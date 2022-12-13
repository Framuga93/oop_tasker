package org.example.model;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileOperationCSV implements FileOperation{
    private String fileName;


    public FileOperationCSV(String fileName) {
        this.fileName = fileName;
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName,true))) {
            writer.flush();
        } catch (IOException ex) {
            throw new IllegalStateException("fileop EX CSV");
        }
    }
    @Override
    public List<String> readAllLines() {
        try{
        CSVReader reader = new CSVReader(new FileReader(fileName), ',');
        HeaderColumnNameMappingStrategy<Task> beanStrategy = new HeaderColumnNameMappingStrategy<>();
        beanStrategy.setType(Task.class);
        CsvToBean<Task> csvToBean = new CsvToBean<>();
        List<Task> tasks = csvToBean.parse(beanStrategy, reader);
        List<String> taskToString = new ArrayList<>();
        tasks.forEach(t->taskToString.add(String.valueOf(t)));
        reader.close();
        return taskToString;
        }catch (IOException e) {
            throw new IllegalStateException("readline EX CSV");
        }

    }

    @Override
    public void saveAllLines(List<String> lines) {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName,false));
            List<Task> tasks = parseCSVWithHeader(fileName);// enter data
            List<String[]> data = toStringArray(tasks); // output datd
            csvWriter.writeAll(data);
            csvWriter.close();

        }

        private static List<String[]> toStringArray(List<Task> tasks) {
            List<String[]> records = new ArrayList<String[]>();
            records.add(new String[]{"id","taskText","date","deadline","priority"});
            Iterator<Task> it = tasks.iterator();
            while (it.hasNext()) {
                Task task = it.next();
                records.add(new String[]{task.getId(), task.getTaskText(), task.getDate(), task.getDeadLine(),task.getPriority()});
            }
            return records;
        }

    }

