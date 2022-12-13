package org.example.model;


public class Task {
    private String id = "";

    private String taskText;

    private String date;

    private String deadLine;

    private String priority;

    public Task(String taskText,String date,String deadLine,String priority) {
        this.taskText = taskText;
        this.deadLine = deadLine;
        this.date = date;
        this.priority = priority;
    }

    public Task(String id, String taskText, String date, String deadLine, String priority) {
        this(taskText,deadLine,date,priority);
        this.id = id;

    }

    public Task() {

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return String.format("Task ID: %s\nTask: %s\nDate start Task: %s\nDeadline: %s\nPriority: %s\n------------------\n",id,taskText,date,deadLine,priority);
    }
}
