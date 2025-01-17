package com.example.EmployeeManagementApp.Controller;

import com.example.EmployeeManagementApp.Model.Employee;
import com.example.EmployeeManagementApp.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

@SpringBootTest
class EmployeeControllerIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employeeRepository.deleteAll(); // Clear DB before each test
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee = new Employee(
                null, "John Doe", "EMP123", "Developer", "IT",
                LocalDate.now(), "ACTIVE", "john.doe@example.com",
                "123 Main Street", "555-1234"
        );
        employeeRepository.save(employee);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("John Doe"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        String jsonPayload = """
                {
                  "fullName": "Jane Doe",
                  "employeeId": "EMP124",
                  "jobTitle": "Tester",
                  "department": "QA",
                  "hireDate": "2025-01-01",
                  "employmentStatus": "ACTIVE",
                  "email": "jane.doe@example.com",
                  "address": "456 Elm Street",
                  "contactInformation": "555-5678"
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Jane Doe"))
                .andExpect(jsonPath("$.employeeId").value("EMP124"));
    }

    @Test
    void testInvalidCreateEmployee() throws Exception {
        String jsonPayload = """
                {
                  "fullName": "",
                  "employeeId": "EMP124",
                  "jobTitle": "Tester",
                  "department": "QA",
                  "hireDate": "2025-01-01",
                  "employmentStatus": "ACTIVE",
                  "email": "invalid-email",
                  "address": "456 Elm Street",
                  "contactInformation": "555-5678"
                }
                """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload))
                .andExpect(status().isBadRequest());
    }
}
