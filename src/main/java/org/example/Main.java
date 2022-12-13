package org.example;

import org.example.controller.UserController;
import org.example.model.*;
import org.example.view.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {//DELETE EX
//        ParserCSV parserCSV = new ParserCSV();
//        parserCSV.parseCSVWithHeader("tasks.csv");  REPFILES
        FileOperation fileOperation = new FileOperationTXT("tasks.txt");
        FileOperation fileOperationCSV = new FileOperationCSV("tasks.csv");
//        Repository repository = new RepositoryFile(fileOperation);
        Repository repository = new RepositoryFile(fileOperationCSV);
        UserController controller = new UserController(repository);
        View view = new View(controller);
        view.run();
    }
}