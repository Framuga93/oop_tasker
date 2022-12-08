package org.example.controller;

import org.example.model.Repository;
import org.example.model.Task;

import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveTask(Task task){
        repository.CreateTask(task);
    }

    public Task readTask(String taskId) throws Exception{
        List<Task> tasks = readTasks();
        for (Task task: tasks){
            if (task.getId().equals(taskId)){
                return task;
            }
        }
        throw new Exception("Task not found");
    }

    public List<Task> readTasks(){
        return repository.getAllTask();
    }

    public void updateTask(Task task){
        repository.updateTask(task);

    }
}
