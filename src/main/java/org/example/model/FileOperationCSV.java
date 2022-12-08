package org.example.model;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperationCSV implements FileOperation{

    private String fileName;

    public FileOperationCSV(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            throw new IllegalStateException("fileop EX");
        }
    }
    @Override
    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            throw new IllegalStateException("readline EX");
        }
        return lines;
    }

    @Override
    public void saveAllLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : lines) {
                writer.write(line);
                writer.append('\n');
            }
            writer.flush();
        } catch (Exception ex) {
            throw new IllegalStateException("saveline EX");
        }
    }
}
