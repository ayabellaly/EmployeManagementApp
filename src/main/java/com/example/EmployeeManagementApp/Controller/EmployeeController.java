package com.example.EmployeeManagementApp.Controller;

import com.example.EmployeeManagementApp.Model.Employee;
import com.example.EmployeeManagementApp.Service.EmployeeService;
import com.example.EmployeeManagementApp.Service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuditService auditService;

    private String getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'HR_PERSONNEL', 'MANAGER')")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String employmentStatus,
            @RequestParam(required = false) LocalDate hireDateStart,
            @RequestParam(required = false) LocalDate hireDateEnd) {

        List<Employee> employees = employeeService.searchEmployees(
                name, employeeId, department, jobTitle, employmentStatus, hireDateStart, hireDateEnd
        );
        return ResponseEntity.ok(employees);
    }





    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'HR_PERSONNEL', 'MANAGER')")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'HR_PERSONNEL', 'MANAGER')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('HR_PERSONNEL')")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        auditService.logAuditTrail("CREATE", "Employee", savedEmployee.getId().toString(), getCurrentUser());
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MANAGER')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            auditService.logAuditTrail("UPDATE", "Employee", updatedEmployee.getId().toString(), getCurrentUser());
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        auditService.logAuditTrail("DELETE", "Employee", id.toString(), getCurrentUser());
        return ResponseEntity.noContent().build();
    }
}
