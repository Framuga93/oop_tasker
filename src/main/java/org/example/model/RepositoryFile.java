package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository{

    private TaskMapper taskMapper = new TaskMapper();
    private FileOperation fileOperation;

    private PriorityStatus priorityStatus;

    public RepositoryFile(FileOperation fileOperation){
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Task> getAllTask() {
        List<String> lines = fileOperation.readAllLines();
        List<Task> tasks = new ArrayList<>();
        lines.forEach(l->tasks.add(taskMapper.map(l)));
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
//        Priority priority = priorityStatus.GetPriorityStatus(task.getDate(),task.getDeadLine());
        long dateDiff = task.getDeadLine().getTime() - task.getDate().getTime();
        int hours = (int) (dateDiff / (60 * 60 * 1000));
        if (hours < 3){
            task.setPriority(Priority.HIGH);
        }
        if (hours < 10){
            task.setPriority(Priority.MIDDLE);
        }
        task.setPriority(Priority.LOW);
//        task.setPriority(priority);
        saveTask(task,tasks);
        return id;
    }

    @Override
    public void deleteTask(String taskId) {

    }

    @Override
    public void updateTask(Task task) {

    }

    private void saveTask(Task task, List<Task> tasks){
        tasks.add(task);
        saveTasks(tasks);
    }

    private void saveTasks(List<Task> tasks){//list task ов
        List<String> lines = new ArrayList<>();
        for (Task task : tasks){
            lines.add(taskMapper.map(task));
        }
        fileOperation.saveAllLines(lines);
    }

}
