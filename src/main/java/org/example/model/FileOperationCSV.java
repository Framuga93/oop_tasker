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
            List<String> allLines = new ArrayList<>();
            String[] row = null;
            while ((row = reader.readNext()) != null) {
                allLines.add(row[0]
                        + "," + row[1]
                        + "," + row[2]
                        + "," + row[3]
                        + "," + row[4]);
            }
        reader.close();
        return allLines;
        }catch (IOException e) {
            throw new IllegalStateException("readline EX CSV");
        }

    }

    @Override
    public void saveAllLines(List<String> lines) {
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, false));
            for (String line : lines) {
                String[] temp;
                temp = line.split(",");
                csvWriter.writeNext(temp);
            }
            csvWriter.close();
        }catch (IOException e) {
            throw new IllegalStateException("readline EX CSV");
        }
        }
    }

