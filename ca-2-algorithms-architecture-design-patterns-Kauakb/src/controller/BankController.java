package controller;

import tree.EmployeeBinaryTree;
import model.Employee;
import utils.MenuEnum;
import view.BankUI;
import utils.SortingAlgorithm;
import utils.FileReader;
import utils.SearchingAlgorithm;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

 //Controller coordinating view and model layers.
 // Loads data
 // Responds to menu choices and invokes utilities


public class BankController {
    private List<Employee> employees;
    private final BankUI view;
    private final EmployeeBinaryTree binaryTree;

    public BankController(BankUI view) {
        this.view = view;
        this.employees = new ArrayList<>();
        this.binaryTree = new EmployeeBinaryTree();
    }

    // Main application loop
    public void run() {
        view.displayWelcome();

        String filename = view.getFileName();
        try {
            // Use EmployeeFileReader to parse CSV
            employees = FileReader.readEmployeesFromFile(filename);
            System.out.println("File read successfully. Loaded: " + employees.size());
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
            return;
        }

        if (employees.isEmpty()) {
            System.out.println("No employees loaded. Exiting...");
            return;
        }

        boolean running = true;
        while (running) {
            MenuEnum choice = view.getMenuChoice();
            if (choice == null) continue;

            switch (choice) {
                case SORT:
                    handleSort();
                    break;
                case SEARCH:
                    handleSearch();
                    break;
                case ADD_RECORDS:
                    handleAddRecords();
                    break;
                case BINARY_TREE:
                    handleBinaryTree();
                    break;
                case EXIT:
                    running = false;
                    System.out.println("Thank you for using KBank system!");
                    break;
            }
        }

        view.close();
    }

    // Sort employees and display first 20
    private void handleSort() {
        System.out.println("\n>>> SORT selected");
        List<Employee> sortedEmployees = SortingAlgorithm.mergeSortByName(employees);
        SortingAlgorithm.displayFirst20(sortedEmployees);
    }

    // Ensure list is sorted then perform binary search
    private void handleSearch() {
        if (employees.isEmpty()) {
            System.out.println("No employees in system!");
            return;
        }
        System.out.println("\n>>> SEARCH selected");
        String searchName = view.getSearchName();
        SearchingAlgorithm.searchAndDisplay(employees, searchName);
    }

    // Add a new employee interactively
    private void handleAddRecords() {
        System.out.println("\n>>> ADD RECORDS selected");
        int sizeBefore = employees.size();
        RecordManager.addNewEmployee(employees, view.getScanner());

        if (employees.size() > sizeBefore ) {
            employees = SortingAlgorithm.mergeSortByName(employees);
            System.out.println("Employee list has been sorted!");

        }
    }

    // Build binary tree from first N records and show stats
    private void handleBinaryTree() {
        System.out.println("\n>>> Create a binary tree selected");
        int numberOfRecords = view.getNumberOfRecords(employees.size());
        if (numberOfRecords == -1) return;

        List<Employee> treeEmployees = new ArrayList<>(employees.subList(0, numberOfRecords));
        binaryTree.insertLevelOrder(treeEmployees);
        binaryTree.levelOrderTraversal();
        binaryTree.displayTreeStats();
    }

    public List<Employee> getEmployees() { return employees; }
}