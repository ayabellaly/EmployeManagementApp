package com.example.EmployeeManagementApp.Repository;

import com.example.EmployeeManagementApp.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByFullNameContainingIgnoreCase(String fullName);

    List<Employee> findByEmployeeIdContainingIgnoreCase(String employeeId);

    List<Employee> findByDepartmentContainingIgnoreCase(String department);

    List<Employee> findByJobTitleContainingIgnoreCase(String jobTitle);

    List<Employee> findByEmploymentStatus(String employmentStatus);

    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);







}
