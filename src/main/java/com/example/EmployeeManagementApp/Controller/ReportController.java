package com.example.EmployeeManagementApp.Controller;

import com.example.EmployeeManagementApp.Model.Employee;
import com.example.EmployeeManagementApp.Service.ReportingService;
import com.example.EmployeeManagementApp.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportingService reportingService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee-report")
    public ResponseEntity<String> generateEmployeeReport(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String employmentStatus) {

        // Retrieve employees based on filters
        List<Employee> employees = employeeService.searchEmployees(
                null, null, department, null, employmentStatus, null, null);

        // Define report filename
        String filename = "employee_report.csv";

        // Generate the report
        reportingService.generateEmployeeReport(filename, employees);

        // Return the file path or a success message
        return ResponseEntity.ok("Report generated successfully: " + filename);
    }
}
