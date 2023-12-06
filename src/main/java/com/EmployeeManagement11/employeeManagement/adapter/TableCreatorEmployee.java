package com.EmployeeManagement11.employeeManagement.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class TableCreatorEmployee {
    private final DataSource dataSource;

    public TableCreatorEmployee(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public void tableCreator() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS employee_manage2 ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    + "emergency_contact VARCHAR(255) NOT NULL,"
                    + "citizenship VARCHAR(255),"
                    + "date INT,"
                    + "contact_renew_date INT,"
                    + "contract_doc VARCHAR(255) NOT NULL,"
                    +"password VARCHAR(255)"
                    + ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        }
    }
    public void tableCreator1(){
        try (Connection connection = dataSource.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS sing_up1 ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255),"
                    +"email VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    +"role VARCHAR(255),"
                    +"password VARCHAR(255)"
                    + ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
