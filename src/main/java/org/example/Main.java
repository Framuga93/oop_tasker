package org.example;

import org.example.controller.UserController;
import org.example.model.FileOperation;
import org.example.model.FileOperationCSV;
import org.example.model.Repository;
import org.example.model.RepositoryFile;
import org.example.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationCSV("tasks.txt");
        Repository repository = new RepositoryFile(fileOperation);
        UserController controller = new UserController(repository);
        View view = new View(controller);
        view.run();
    }
}