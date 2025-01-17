package com.example.EmployeeManagementApp.Repository;

import com.example.EmployeeManagementApp.Model.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
}
