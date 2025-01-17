package com.example.EmployeeManagementApp.Service;

import com.example.EmployeeManagementApp.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ReportingService {

    @Autowired
    private EmployeeService employeeService;

    public void generateEmployeeReport(String filename, List<Employee> employees) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write header
            writer.append("ID,Full Name,Employee ID,Job Title,Department,Email,Employment Status,Hire Date\n");

            // Write employee data
            for (Employee employee : employees) {
                writer.append(employee.getId().toString()).append(",")
                        .append(employee.getFullName()).append(",")
                        .append(employee.getEmployeeId()).append(",")
                        .append(employee.getJobTitle()).append(",")
                        .append(employee.getDepartment()).append(",")
                        .append(employee.getEmail() != null ? employee.getEmail() : "").append(",")
                        .append(employee.getEmploymentStatus()).append(",")
                        .append(employee.getHireDate().toString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
