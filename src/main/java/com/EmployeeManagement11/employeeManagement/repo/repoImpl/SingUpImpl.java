package com.EmployeeManagement11.employeeManagement.repo.repoImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.enitiy.Roles;
import com.EmployeeManagement11.employeeManagement.enitiy.SingUp;
import com.EmployeeManagement11.employeeManagement.repo.SingUpQuery;
import employee.UserRolesOuterClass;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class SingUpImpl implements SingUpQuery {
    private final DataSource dataSource;

    public SingUpImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createSingUp(SingUp singUp,Roles roles) {
        try(Connection connection= dataSource.getConnection()) {
            String query="INSERT INTO sing_up1 (name,email,phone,role,password) VALUES(?,?,?,?,?)";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setString(1,singUp.getName());
                statement.setString(2,singUp.getEmail());
                statement.setString(3, singUp.getPhone());
                statement.setString(4, roles.name());
                statement.setString(5,singUp.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SingUp findUserByName(String name) {
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

    public SingUp toEntity(ResultSet resultSet) throws SQLException {
        String name=resultSet.getString("name");
        String email= resultSet.getString("email");
        String phone= resultSet.getString("phone");
        String password= resultSet.getString("password");
        Roles role= Roles.valueOf(resultSet.getString("role"));
        return new SingUp(name,email,phone,role,password);
    }
}
