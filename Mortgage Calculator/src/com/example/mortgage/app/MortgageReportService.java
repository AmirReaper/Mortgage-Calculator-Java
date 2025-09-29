package com.example.app;

import com.example.model.Mortgage;
import com.example.service.ConsoleReport;
import com.example.service.FileReportExporter;
import com.example.service.Exportable;
import com.example.exception.ExportException;

/**
 * Generates and exports reports for mortgages
 */
public class MortgageReportService {

    public static void generateReport(Mortgage mortgage) {
        ConsoleReport report = new ConsoleReport(mortgage);
        report.printMortgage();
        report.printPaymentSchedule();

        try {
            FileReportExporter exporter = new FileReportExporter("mortgage_report.txt");
            exporter.exportToFile((Exportable) report);
            System.out.println("Report exported to mortgage_report.txt");
        } catch (ExportException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}