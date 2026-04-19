package utils;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileReader {
    public static List<Employee> readEmployeesFromFile(String filename) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filename))) {
            String line;
            boolean firstLine = true; // skip header

            while ((line = reader.readLine()) != null) {
                // skip blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                if (firstLine) {
                    firstLine = false;
                    continue; // assume header row
                }

                // Defensive split: limit to 9 parts (the CSV has 9 columns)
                String[] parts = line.split(",", 9);

                // Trim each part and guard missing fields
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i] != null) parts[i] = parts[i].trim();
                }

                // Safely extract fields with bounds checks
                String firstName = parts.length > 0 ? parts[0] : "";
                String lastName  = parts.length > 1 ? parts[1] : "";
                String gender    = parts.length > 2 ? parts[2] : "";
                String email     = parts.length > 3 ? parts[3] : "";

                // Parse salary safely: default to 0.0 on parse error or missing field
                double salary = 0.0;
                if (parts.length > 4 && parts[4] != null && !parts[4].isEmpty()) {
                    try {
                        salary = Double.parseDouble(parts[4]);
                    } catch (NumberFormatException nfe) {
                        System.err.println("Warning: malformed salary '" + parts[4] + "' — defaulting to 0.0");
                        salary = 0.0;
                    }
                }

                String department = parts.length > 5 ? parts[5] : "";
                String position   = parts.length > 6 ? parts[6] : ""; // mapped to managerType
                String jobTitle   = parts.length > 7 ? parts[7] : "";
                String company    = parts.length > 8 ? parts[8] : "";

                // Create Employee (ensure this matches your Employee constructor order)
                Employee emp = new Employee(firstName, lastName, email, salary, department, position, jobTitle, company);
                employees.add(emp);
            }

            System.out.println("File read successfully. Records loaded: " + employees.size());
            return employees;

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return employees;
    }
}