package org.example.view;

import org.example.controller.UserController;
import org.example.model.Priority;
import org.example.model.PriorityStatus;
import org.example.model.Task;
import org.example.model.UserMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    private final UserController userController;
    private final UserMapper mapper = new UserMapper();

    private final PriorityStatus priorityStatus = new PriorityStatus();

    public View(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;
        while (true){
            try {
                String command = prompt("Enter command: ");
                com = Commands.valueOf(command.toUpperCase());
            }catch (Exception e){
                throw new IllegalStateException("Unknown command");
            }
        if (com == Commands.EXIT) return;
        switch (com) {
            case CREATE:
//                try {
                    List<String> data = getData();
                    userController.saveTask(new Task(data.get(0), data.get(1), data.get(2), data.get(3)));
//                } catch (Exception e) {
//                    throw new IllegalStateException("Incorrect data");
//                }
                break;
            case READ:
                String id = prompt("Enter task ID: ");
                try {
                    Task task = userController.readTask(id);
                    System.out.println(task);
                } catch (Exception e) {
                    throw new IllegalStateException("User not found");
                }
                break;
            case LIST:
                userController.readTasks().forEach(System.out::println);
                break;
            case UPDATE:
                try {
                    id = prompt("Enter Task ID to update: ");
                    data = getData();
                    userController.updateTask(new Task(id,data.get(0), data.get(1), data.get(2), data.get(3)));
                    break;
                }catch (Exception e) {
                    throw new IllegalStateException("User not found");
                }
            case DELETE:
                id = prompt("Enter task ID to delete: ");
                try {
                    userController.readTask(id);
                }catch (Exception e){
                    throw new IllegalStateException("Task not found");
                }
                String answ = prompt("Are you sure?\nYES\nNO: ");
                if (answ.equalsIgnoreCase("YES")){
                    userController.deleteTask(id);
            }
                if (answ.equalsIgnoreCase("NO")){
                    break;
                }
        }
        }


    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private List<String> getData(){
        List<String> data = new ArrayList<>();
        String taskText = prompt("Task text: ");
        String deadLineStr = prompt("Deadline format dd.MM.yyyy : ");
        Date deadLine = mapper.dateMap(deadLineStr);
        Date dateNowN = new Date();
        Priority priority = priorityStatus.GetPriorityStatus(dateNowN,deadLine);
        String dateNow = mapper.dateMap(dateNowN);
        data.add(taskText);
        data.add(dateNow);
        data.add(deadLineStr);
        data.add(String.valueOf(priority));
        return data;
    }

}
