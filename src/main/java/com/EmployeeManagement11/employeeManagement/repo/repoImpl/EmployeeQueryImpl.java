package com.EmployeeManagement11.employeeManagement.repo.repoImpl;

import com.EmployeeManagement11.employeeManagement.enitiy.Employee;
import com.EmployeeManagement11.employeeManagement.repo.EmployeeQuery;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeQueryImpl implements EmployeeQuery {
    private final DataSource dataSource;

    public EmployeeQueryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveData(Employee employee) {
        try(Connection connection= dataSource.getConnection()) {
            String query="INSERT INTO employee_manage2(id,name,phone,emergency_contact" +
                    ",citizenship,date,contact_renew_date,contract_doc) VALUES (?,?,?,?,?,?,?,?)";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setInt(1,employee.getId());
                statement.setString(2, employee.getName());
                statement.setString(3, employee.getPhone());
                statement.setString(4, employee.getEmergency_contact());
                statement.setString(5, employee.getCitizenship());
                statement.setInt(6,employee.getDate());
                statement.setInt(7,employee.getContact_renew_date());
                statement.setString(8,employee.getContract_doc());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteData(int id) {
        try(Connection connection= dataSource.getConnection()) {
            String query="DELETE FROM employee_manage2 WHERE id=?";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setInt(1,id);
                statement.executeQuery();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Employee getData(int id) {
        try(Connection connection= dataSource.getConnection()) {
            String query="SELECT * FROM employee_manage2 WHERE id=?";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setInt(1,id);
                ResultSet resultSet=statement.executeQuery();

                if(resultSet.next()){
                    return toEmployee(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Employee updateData(Employee employee) {
        try(Connection connection= dataSource.getConnection()) {
            String query="UPDATE employee_manage2 SET id=?,name=?,phone=?,emergency_contact=?,citizenship=?,date=?,contact_renew_date=?,contract_doc=? WHERE id=?";
            try(PreparedStatement statement= connection.prepareStatement(query)){
                statement.setInt(1,employee.getId());
                statement.setString(2, employee.getName());
                statement.setString(3, employee.getPhone());
                statement.setString(4,employee.getEmergency_contact());
                statement.setString(5,employee.getCitizenship());
                statement.setInt(6, employee.getDate());
                statement.setInt(7, employee.getContact_renew_date());
                statement.setString(8,employee.getContract_doc());
                statement.setInt(9,employee.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }



    //    @Override
//    public List<Employee> getALlData() {
//        List<Employee> employees;
//        try (Connection connection = dataSource.getConnection()) {
//            String query = "SELECT * FROM employee_manage";
//            employees = new ArrayList<>();
//            try (PreparedStatement statement = connection.prepareStatement(query)) {
//                ResultSet resultSet = statement.executeQuery();
//                while (resultSet.next()) {
//                    employees.add(toEmployee(resultSet));
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return employees;
//    }
    public Employee toEmployee(ResultSet resultSet) throws SQLException {
        int id1=resultSet.getInt("id");
        String name1= resultSet.getString("name");
        String phone1=resultSet.getString("phone");
        String emer= resultSet.getString("emergency_contact");
        String citi= resultSet.getString("citizenship");
        int data1= resultSet.getInt("date");
        int date2= resultSet.getInt("contact_renew_date");
        String data3= resultSet.getString("contract_doc");
        String pass=resultSet.getString("password");
        return new Employee(id1,name1,phone1,emer,citi,data1,date2,data3,pass);

    }
}
