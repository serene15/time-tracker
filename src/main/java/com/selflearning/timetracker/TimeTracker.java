package com.selflearning.timetracker;

import com.selflearning.timetracker.data.Category;
import com.selflearning.timetracker.data.CurrentTasks;
import com.selflearning.timetracker.data.Task;
import com.selflearning.timetracker.util.ArgUtil;
import com.selflearning.timetracker.util.Args;
import com.selflearning.timetracker.util.FileUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class TimeTracker {

    public static void main(String[] args)  {
        ArgUtil argUtil = new ArgUtil();
        Args arguments = argUtil.parseArgs(args);

        FileUtil fileUtil = new FileUtil();

        CurrentTasks currentTasks = null;
        try {
            currentTasks = fileUtil.getSavedTasks();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (arguments.getCommand()){
            case TASK_START -> {
                Task task = new Task(arguments.getTaskName(), new Category(arguments.getCategoryName()));
                currentTasks.startTask(task);

            }
            case TASK_STOP -> {
                currentTasks.completeTask(arguments.getTaskName());
            }
            case REPORT_TASKS -> {
                System.out.println(currentTasks.getTaskReport());
            }
            case REPORT_CATEGORIES -> {
                currentTasks.getCategoryReport();
            }
        }
        try {
            fileUtil.saveTasksToFile(currentTasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
