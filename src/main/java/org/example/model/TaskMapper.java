package org.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskMapper {

    public String map(Task task){
        return String.format("%s,%s,%s,%s,%s",task.getId(),task.getTaskText(),
                task.getDate(),task.getDeadLine(),task.getPriority());
    }

    public Task map(String line){

        String[] lines = line.split(",");
        return new Task(lines[0],lines[1],lines[2],lines[3],lines[4]);
    }

    public Date deadLineMap(String date)  {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            return dateFormat.parse(date);
        }catch (Exception e){
            throw new IllegalStateException("dl map EX");
        }
    }
}
