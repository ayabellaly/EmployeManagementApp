package com.example.EmployeeManagementApp.Model;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    private Validator validator;

    public EmployeeTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void testValidEmployee() {
        Employee employee = new Employee(
                1L, "John Doe", "EMP123", "Developer", "IT",
                LocalDate.now(), "ACTIVE", "john.doe@example.com",
                "123 Main Street", "555-1234"
        );

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidEmail() {
        Employee employee = new Employee(
                1L, "John Doe", "EMP123", "Developer", "IT",
                LocalDate.now(), "ACTIVE", "invalid-email",
                "123 Main Street", "555-1234"
        );

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
    }

    @Test
    void testMissingFullName() {
        Employee employee = new Employee(
                1L, "", "EMP123", "Developer", "IT",
                LocalDate.now(), "ACTIVE", "john.doe@example.com",
                "123 Main Street", "555-1234"
        );

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
    }

    @Test
    void testInvalidEmploymentStatus() {
        Employee employee = new Employee(
                1L, "John Doe", "EMP123", "Developer", "IT",
                LocalDate.now(), "ON_LEAVE", "john.doe@example.com",
                "123 Main Street", "555-1234"
        );

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        assertEquals(1, violations.size());
    }
}
