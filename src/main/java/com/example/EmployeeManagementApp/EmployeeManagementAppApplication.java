package com.example.EmployeeManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

@SpringBootApplication

public class EmployeeManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementAppApplication.class, args);

		// Start Swing UI
		SwingUtilities.invokeLater(() -> new EmployeeManagementUI().initialize());
	}
}