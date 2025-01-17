package com.example.EmployeeManagementApp.Service;

import com.example.EmployeeManagementApp.Model.AuditTrail;
import com.example.EmployeeManagementApp.Repository.AuditTrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    @Autowired
    private AuditTrailRepository auditTrailRepository;

    public void logAuditTrail(String action, String entity, String entityId, String changedBy) {
        AuditTrail auditTrail = new AuditTrail(action, entity, entityId, changedBy, LocalDateTime.now());
        auditTrailRepository.save(auditTrail);
    }
}
