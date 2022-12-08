package org.example.model;

import java.util.Date;

public class PriorityStatus {
    private Date now;
    private Date deadLine;

    public PriorityStatus(Date now, Date deadLine){
        this.now = now;
        this.deadLine = deadLine;
    }

    public Priority GetPriorityStatus(Date now, Date deadLine) {
        System.out.println("1");
        long dateDiff = deadLine.getTime() - now.getTime();
        int hours = (int) (dateDiff / (60 * 60 * 1000));
        if (hours < 3){
            return Priority.HIGH;
        }
        if (hours < 10){
            return Priority.MIDDLE;
        }
        return Priority.LOW;
    }
    }

