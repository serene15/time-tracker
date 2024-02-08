package com.selflearning.timetracker.util;

import com.selflearning.timetracker.data.Category;
import com.selflearning.timetracker.data.CurrentTasks;
import com.selflearning.timetracker.data.Task;
import com.selflearning.timetracker.data.TaskStatus;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtil {

    public static final String PATH = "task-info.csv";
    public CurrentTasks getSavedTasks() throws URISyntaxException, IOException {

        Path path = Paths.get(PATH);
        if(Files.notExists(path)){
            Files.createFile(path);
        }

        Map<String, Task> taskMap = Files.lines(path)
                .map(line -> line.split(","))
                .filter(tokenArray -> tokenArray.length == 5)
                .map(tokenArray -> new Task(
                        tokenArray[0],
                        new Category(tokenArray[1]),
                        LocalDateTime.parse(tokenArray[2]),
                        tokenArray[3] == null || "null".equals(tokenArray[3]) || tokenArray[3].isBlank() ? null : LocalDateTime.parse(tokenArray[3]),
                        TaskStatus.valueOf(tokenArray[4])
                ))
                .collect(Collectors.toMap(Task::getTaskName, Function.identity()));

        return new CurrentTasks(taskMap);
    }

    public void saveTasksToFile(CurrentTasks currentTasks) throws IOException{

        Path path = Paths.get(PATH);
        if(Files.notExists(path)){
            Files.createFile(path);
        }
       List<String> lines = currentTasks.getCurrentTasks()
                .values()
                .stream()
                .map(Task::getCSVFormat)
                .toList();

        Files.write(path, lines);
    }
}
