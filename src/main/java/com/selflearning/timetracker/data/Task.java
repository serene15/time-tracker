package com.selflearning.timetracker.data;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

    private String taskName;
    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus taskStatus;

    public Task(){

    }

    public Task(String taskName, Category category) {
        this.taskName = taskName;
        this.category = category;
        this.startTime = LocalDateTime.now();
        this.taskStatus = TaskStatus.IN_PROGRESS;
    }

    public Task(String taskName, Category category, LocalDateTime startTime, LocalDateTime endTime, TaskStatus taskStatus) {
        this.taskName = taskName;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getendTime() {
        return endTime;
    }

    public void setendTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", category=" + category.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", taskStatus=" + taskStatus +
                '}' + "\n";
    }

    public String getCSVFormat(){
        return taskName + "," + category.getName() + "," + startTime + "," + endTime + "," + taskStatus ;
    }

    public String getTaskDuration(){
        if(this.getendTime() == null){
            return null;
        }
        return Duration.between(this.getStartTime(),this.getendTime()).toMinutes() + " minutes";
    }
}
