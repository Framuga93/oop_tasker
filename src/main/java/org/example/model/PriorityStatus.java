package org.example.model;

import java.util.Date;

public class PriorityStatus {


    public Priority GetPriorityStatus(Date now, Date deadLine) {
        long dateDiff = deadLine.getTime() - now.getTime();
        int days = (int) (dateDiff / (24 * 60 * 60 * 1000));
        if (days < 3){
            return Priority.HIGH;
        }
        if (days < 10){
            return Priority.MIDDLE;
        }
        return Priority.LOW;
    }
    }

