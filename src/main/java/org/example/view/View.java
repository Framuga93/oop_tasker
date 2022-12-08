package org.example.view;

import org.example.controller.UserController;
import org.example.model.Task;
import org.example.model.TaskMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class View {

    private UserController userController;
    private TaskMapper taskMapper;

    public View(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;
        while (true){
        String command = prompt("Enter command: ");
        com = Commands.valueOf(command.toUpperCase());
        if (com == Commands.EXIT) return;
        switch (com) {
            case CREATE:
                try {
                    String taskText = prompt("Task text: ");
                    String stdeadLine = prompt("Deadline: ");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date deadLine = dateFormat.parse("11.12.2022");
//                    Date deadLine = taskMapper.deadLineMap(stdeadLine);
                    Date dateNowN = new Date();
                    Date dateNow = dateFormat.parse(dateNowN);
                    userController.saveTask(new Task(taskText, dateNow, deadLine));
                } catch (IllegalStateException | ParseException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case READ:
                String id = prompt("Enter task ID: ");
                try {
                    Task task = userController.readTask(id);
                    System.out.println(task);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case LIST:
                userController.readTasks().forEach(System.out::println);
                break;
            case UPDATE:
                try {
                    String taskText = prompt("Task text: ");
                    String stdeadLine = prompt("Deadline: ");
                    Date deadLine = taskMapper.deadLineMap(stdeadLine);
                    Date dateNow = new Date();
                    userController.saveTask(new Task(taskText, dateNow, deadLine));
                } catch (Exception e) {
                    throw new RuntimeException();
                }
        }
        }


    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }


}
