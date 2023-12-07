package com.EmployeeManagement11.employeeManagement.repo.repoImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SignUp;
import com.EmployeeManagement11.employeeManagement.repo.SignUpQuery;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class SignUpImpl implements SignUpQuery {
    private final DataSource dataSource;

    public SignUpImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createSignUp(SignUp signUp, Roles roles) {
        try(Connection connection= dataSource.getConnection()) {
            String query="INSERT INTO sing_up1 (name,email,phone,role,password) VALUES(?,?,?,?,?)";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setString(1,signUp.getName());
                statement.setString(2,signUp.getEmail());
                statement.setString(3, signUp.getPhone());
                statement.setString(4, roles.name());
                statement.setString(5,signUp.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SignUp findUserByName(String name) {
        try(Connection connection= dataSource.getConnection()) {
            String query="SELECT * FROM sing_up1 WHERE name='"+name+"'";
            try(PreparedStatement statement= connection.prepareStatement(query)){
               ResultSet resultSet= statement.executeQuery();
               if(resultSet.next()){
                   return toEntity(resultSet);
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public SignUp toEntity(ResultSet resultSet) throws SQLException {
        String name=resultSet.getString("name");
        String email= resultSet.getString("email");
        String phone= resultSet.getString("phone");
        String password= resultSet.getString("password");
        Roles role= Roles.valueOf(resultSet.getString("role"));
        return new SignUp(name,email,phone,role,password);
    }
}
