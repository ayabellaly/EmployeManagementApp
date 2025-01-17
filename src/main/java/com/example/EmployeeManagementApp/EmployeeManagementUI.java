package com.example.EmployeeManagementApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementUI {

    private JFrame frame;
    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementUI().initialize());
    }

    public void initialize() {
        // Main Frame
        frame = new JFrame("Employee Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Add Header Panel (using MigLayout)
        JPanel headerPanel = new JPanel(new net.miginfocom.swing.MigLayout("", "[grow][grow]", "[grow]"));
        JLabel titleLabel = new JLabel("Employee Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, "span, growx, wrap");
        frame.add(headerPanel, BorderLayout.NORTH);

        // Table for Employee Data (using GridBagLayout)
        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;

        String[] columnNames = {"ID", "Name", "Department", "Job Title", "Employment Status", "Hire Date"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        tablePanel.add(tableScrollPane, gbc);
        frame.add(tablePanel, BorderLayout.CENTER);

        // Button Panel (using MigLayout)
        JPanel buttonPanel = new JPanel(new net.miginfocom.swing.MigLayout("", "[grow, fill][grow, fill][grow, fill][grow, fill]", ""));
        JButton fetchEmployeesButton = new JButton("Fetch Employees");
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton updateEmployeeButton = new JButton("Update Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        buttonPanel.add(fetchEmployeesButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(updateEmployeeButton);
        buttonPanel.add(deleteEmployeeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        fetchEmployeesButton.addActionListener(e -> fetchEmployees());
        addEmployeeButton.addActionListener(e -> addEmployee());
        updateEmployeeButton.addActionListener(e -> updateEmployee());
        deleteEmployeeButton.addActionListener(e -> deleteEmployee());

        frame.setVisible(true);
    }

    private void fetchEmployees() {
        JOptionPane.showMessageDialog(frame, "Fetch Employees functionality is not implemented yet.");
    }

    private void addEmployee() {
        JOptionPane.showMessageDialog(frame, "Add Employee functionality is not implemented yet.");
    }

    private void updateEmployee() {
        JOptionPane.showMessageDialog(frame, "Update Employee functionality is not implemented yet.");
    }

    private void deleteEmployee() {
        JOptionPane.showMessageDialog(frame, "Delete Employee functionality is not implemented yet.");
    }
}
