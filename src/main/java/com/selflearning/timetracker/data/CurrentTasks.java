package com.selflearning.timetracker.data;

import com.selflearning.timetracker.Logger;
import com.selflearning.timetracker.data.Task;
import com.selflearning.timetracker.data.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrentTasks {

    private Map<String, Task> currentTasks = new HashMap<>();

    public void startTask(Task task){
        if (currentTasks.putIfAbsent(task.getTaskName(), task) != null){
            Logger.log("Task already exists, skipping");
        }
    }

    public void completeTask(String taskName){
        Task existingTask = currentTasks.get(taskName);
        if(existingTask == null) {
            Logger.log("No tasks found");
        }
        else{
            existingTask.setendTime(LocalDateTime.now());
            existingTask.setTaskStatus(TaskStatus.COMPLETE);
        }
    }

    public Map<String, String> getTaskReport(){
        return  currentTasks
                .values()
                .stream()
                .filter(task -> task.getendTime()!=null)
                .collect(Collectors.toMap(Task::getTaskName,Task::getTaskDuration));
    }

    public void getCategoryReport(){

        Map<String,Duration> categoryReport = new HashMap<>();

        currentTasks
                .values()
                .stream()
                .filter(task -> task.getendTime()!=null)
                .forEach(task -> {
                    Duration categoryDuration = categoryReport.getOrDefault(task.getCategory().getName(),Duration.ZERO);
                    categoryReport.put(task.getCategory().getName(),categoryDuration.plus(Duration.between(task.getStartTime(),task.getendTime())));
                });

        categoryReport.forEach((cat,dur) -> System.out.println(cat + " tasks went on for " + dur.toMinutes() + " minutes"));

    }
    public Map<String, Task> getCurrentTasks() {
        return currentTasks;
    }

    public void setCurrentTasks(Map<String, Task> currentTasks) {
        this.currentTasks = currentTasks;
    }

    @Override
    public String toString() {
        return "CurrentTasks{" +
                "currentTasks=\n" + currentTasks +
                '}';
    }

    public CurrentTasks(Map<String, Task> currentTasks) {
        this.currentTasks = currentTasks;
    }
}
