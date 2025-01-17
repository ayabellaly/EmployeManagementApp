package com.example.EmployeeManagementApp.Service;

import com.example.EmployeeManagementApp.Model.Employee;
import com.example.EmployeeManagementApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setFullName(updatedEmployee.getFullName());
            employee.setEmployeeId(updatedEmployee.getEmployeeId());
            employee.setJobTitle(updatedEmployee.getJobTitle());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setHireDate(updatedEmployee.getHireDate());
            employee.setEmploymentStatus(updatedEmployee.getEmploymentStatus());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setContactInformation(updatedEmployee.getContactInformation());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }







    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }




    //search and filter


    public List<Employee> searchEmployees(String name, String employeeId, String department, String jobTitle,
                                          String employmentStatus, LocalDate hireDateStart, LocalDate hireDateEnd) {
        if (name != null && !name.isEmpty()) {
            return employeeRepository.findByFullNameContainingIgnoreCase(name);
        } else if (employeeId != null && !employeeId.isEmpty()) {
            return employeeRepository.findByEmployeeIdContainingIgnoreCase(employeeId);
        } else if (department != null && !department.isEmpty()) {
            return employeeRepository.findByDepartmentContainingIgnoreCase(department);
        } else if (jobTitle != null && !jobTitle.isEmpty()) {
            return employeeRepository.findByJobTitleContainingIgnoreCase(jobTitle);
        } else if (employmentStatus != null && !employmentStatus.isEmpty()) {
            return employeeRepository.findByEmploymentStatus(employmentStatus);
        } else if (hireDateStart != null && hireDateEnd != null) {
            return employeeRepository.findByHireDateBetween(hireDateStart, hireDateEnd);
        } else {
            return employeeRepository.findAll(); // Return all employees if no filter is applied
        }
    }









}
