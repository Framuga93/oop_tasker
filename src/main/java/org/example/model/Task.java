package org.example.model;

import java.util.Date;


public class Task {

    private TaskMapper taskMapper = new TaskMapper();

    private String id = "";

    private String taskText;

    private Date date;

    private Date deadLine;

    private Priority priority;

    public Task(String taskText,Date date,Date deadLine) {
        this.taskText = taskText;
        this.deadLine = deadLine;
        this.date = date;
    }

    public Task(String id, String taskText, Date date,Date deadLine, Priority priority){
        this(taskText,date,deadLine);
        this.id = id;
        this.priority = priority;
    }

    public Task(String id, String taskText, String date, String deadLine, String priority) {
        this.id = id;
        this.taskText = taskText;
        this.deadLine = taskMapper.deadLineMap(deadLine);
        this.date = taskMapper.deadLineMap(date);
        this.priority = Priority.valueOf(priority);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return String.format("Task ID: %s\nTask: %s\nDate start Task: %s\nDeadline: %s\nPriority: %s",id,taskText,date,deadLine,priority);
    }
}
