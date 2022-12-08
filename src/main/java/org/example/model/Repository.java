package org.example.model;

import java.util.List;

public interface Repository {
    List<Task> getAllTask();

    String CreateTask(Task task);

    void deleteTask(String taskId);

    void updateTask(Task task);


}
