package com.example.EmployeeManagementApp.Service;

import com.example.EmployeeManagementApp.Model.Employee;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportingService {

    public void generatePdfReport(String filename, List<Employee> employees) throws DocumentException, IOException {
        // Create a Document instance for the PDF
        Document document = new Document();

        // Create PdfWriter instance to write the document to a file
        PdfWriter.getInstance(document, new FileOutputStream(filename));

        // Open the document to start adding content
        document.open();

        // Create a font for the table cells
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Create a table with 8 columns
        PdfPTable table = new PdfPTable(8);

        // Add table headers
        table.addCell(new Phrase("ID", font));
        table.addCell(new Phrase("Full Name", font));
        table.addCell(new Phrase("Employee ID", font));
        table.addCell(new Phrase("Job Title", font));
        table.addCell(new Phrase("Department", font));
        table.addCell(new Phrase("Email", font));
        table.addCell(new Phrase("Employment Status", font));
        table.addCell(new Phrase("Hire Date", font));

        // Add employee data to the table
        for (Employee employee : employees) {
            table.addCell(employee.getId().toString());
            table.addCell(employee.getFullName());
            table.addCell(employee.getEmployeeId());
            table.addCell(employee.getJobTitle());
            table.addCell(employee.getDepartment());
            table.addCell(employee.getEmail() != null ? employee.getEmail() : "");
            table.addCell(employee.getEmploymentStatus());
            table.addCell(employee.getHireDate().toString());
        }

        // Add the table to the document
        document.add(table);

        // Close the document to finalize the PDF creation
        document.close();
    }
}
