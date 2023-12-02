package com.EmployeeManagement11.employeeManagement.adapter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AppStartUpRunner implements CommandLineRunner {
    private final TableCreatorEmployee tableCreatorClass;

    public AppStartUpRunner(TableCreatorEmployee tableCreatorClass) {
        this.tableCreatorClass = tableCreatorClass;
    }

    @Override
    public void run(String... args) throws Exception {
        tableCreatorClass.tableCreator();

    }
}