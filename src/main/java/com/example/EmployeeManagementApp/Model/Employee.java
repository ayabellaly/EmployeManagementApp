package com.example.EmployeeManagementApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Full Name is required")
    @Size(min = 3, max = 100, message = "Full Name must be between 3 and 100 characters")
    private String fullName;

    @NotEmpty(message = "Employee ID is required")
    @Size(min = 3, max = 20, message = "Employee ID must be between 3 and 20 characters")
    private String employeeId;

    @NotEmpty(message = "Job Title is required")
    private String jobTitle;

    @NotEmpty(message = "Department is required")
    private String department;

    @NotNull(message = "Hire Date is required")
    private LocalDate hireDate;

    @NotNull(message = "Employment Status is required")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Employment Status must be either ACTIVE or INACTIVE")
    private String employmentStatus;

    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Contact Information is required")
    private String contactInformation;


}
