package com.example;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Exports a MortgageReport to a file using try-with-resources.
 */
public class FileReportExporter {
    private final String filename;

    public FileReportExporter(String filename) {
        this.filename = filename;
    }

    public void exportToFile(Exportable report) throws ExportException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(report.export());
        } catch (IOException e) {
            throw new ExportException("📛Failed to export report to file: " + filename, e);
        }
    }
}