package org.example.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMapper {

    public String map(Task task){
        return String.format("%s,%s,%s,%s,%s",task.getId(),task.getTaskText(),
                task.getDate(),task.getDeadLine(),task.getPriority());
    }

    public Task map(String line){

        String[] lines = line.split(",");
        return new Task(lines[0],lines[1],lines[2],lines[3],lines[4]);
    }

    public Date dateMap(String date)  {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.parse(date);
        }catch (Exception e){
            throw new IllegalStateException("Incorrect format");
        }
    }

    public String dateMap(Date date){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(date);
        }catch (Exception e){
            throw new IllegalStateException("Incorrect format");
        }
    }
}
