package org.example.model;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ParserCSV {
    public List<Task> parseCSVWithHeader(String fileName) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName), ',');

        HeaderColumnNameMappingStrategy<Task> beanStrategy = new HeaderColumnNameMappingStrategy<Task>();
        beanStrategy.setType(Task.class);

        CsvToBean<Task> csvToBean = new CsvToBean<Task>();
        List<Task> tasks = csvToBean.parse(beanStrategy, reader);

        System.out.println(tasks);
        reader.close();

        return tasks;
    }

    public void writter(String fileName) throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer);
        List<Task> tasks = parseCSVWithHeader(fileName);// enter data
        List<String[]> data = toStringArray(tasks); // output datd
        csvWriter.writeAll(data);
        csvWriter.close();
        System.out.println(writer);

    }

    private static List<String[]> toStringArray(List<Task> emps) {
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[]{"id","taskText","date","deadline","priority"});
        Iterator<Task> it = emps.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            records.add(new String[]{task.getId(), task.getTaskText(), task.getDate(), task.getDeadLine(),task.getPriority()});
        }
        return records;
    }
}
