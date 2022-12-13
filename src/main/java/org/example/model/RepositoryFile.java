package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository{

    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation){
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Task> getAllTask() {
        List<String> lines = fileOperation.readAllLines();
        List<Task> tasks = new ArrayList<>();
        lines.forEach(l->tasks.add(mapper.map(l)));
        return tasks;
    }

    @Override
    public String CreateTask(Task task) {
        List<Task> tasks = getAllTask();
        int max = 0;
        for (Task elem : tasks){
            int id = Integer.parseInt(elem.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        task.setId(id);
        saveTask(task,tasks);
        return id;
    }

    @Override
    public void deleteTask(String taskId) {
    List<Task> tasks = getAllTask();
    tasks.remove(findTask(taskId,tasks));
    saveTasks(tasks);
    }

    @Override
    public void updateTask(Task task) {
    deleteTask(task.getId());
    List<Task> tasks = getAllTask();
    saveTask(task,tasks);
    }

    private void saveTask(Task task, List<Task> tasks){
        tasks.add(task);
        saveTasks(tasks);
    }

    private void saveTasks(List<Task> tasks){
        List<String> lines = new ArrayList<>();
        tasks.forEach(t-> lines.add(mapper.map(t)));
        fileOperation.saveAllLines(lines);
    }

    private Task findTask(String taskId, List<Task> tasks){
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        throw new IllegalStateException("User not found");
    }

}
