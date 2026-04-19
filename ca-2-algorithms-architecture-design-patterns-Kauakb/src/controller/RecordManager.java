package controller;

import model.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.List;
import java.util.Set;

public class RecordManager {
    //get all unique departments from existing records

public static Set<String> getAvailableDepartments(List<Employee> employees){
    Set<String> departments = new HashSet<String>();
    for (Employee emp: employees) {
        if(emp.getDepartment() != null && !emp.getDepartment().isEmpty()) {
            departments.add(emp.getDepartment());
        }
    }
    return departments;
}

//get all managers types
    public static Set<String> getAvailableManagerTypes(List<Employee> employees){
        Set<String> managerTypes = new HashSet<>();
        for (Employee emp : employees){
            if(emp.getManagerType() != null && !emp.getManagerType().isEmpty()) {
                managerTypes.add(emp.getManagerType());
            }
        }
        return managerTypes;
    }

    // add new employee w validation
    public static void addNewEmployee(List<Employee> employees, Scanner scanner){
        System.out.println("Please enter the name of the employee you would like to add: ");

        //input name
        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine(). trim(); //The trim method removes whitespace from both ends of a string.
        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine(). trim();

        //validation for name's input
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Error: name can not be empty");
            return;
        }

        //show available departments
        Set<String> departments = getAvailableDepartments(employees);
        System.out.println("\nAvailable Departments: ");
        List<String> deptList = new ArrayList<>(departments);
        Collections.sort(deptList);
        for (int i = 0; i <deptList.size(); i++){
            System.out.println((i + 1) + ".  " + deptList.get(i));
        }

        System.out.println("Select Department (number): ");
        int deptChoice;
        try{
            deptChoice = Integer.parseInt(scanner.nextLine());
            if (deptChoice < 1 || deptChoice > deptList.size()) {
                System.out.println("Error: invalid department choice!");
                return;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid Input!");
            return;
        }
        String selectedDepartment = deptList.get(deptChoice - 1);

        //showing available manager type
        Set<String> managerTypes = getAvailableManagerTypes(employees);
        System.out.println("\nJob Type: ");
        List<String> managerList = new ArrayList<>(managerTypes);
        Collections.sort(managerList);
        for (int i = 0; i <managerList.size(); i++){
            System.out.println((i + 1) + ".  " + managerList.get(i));
        }

        System.out.println("Select Manager (number): ");
        int managerChoice;
        try{
            managerChoice = Integer.parseInt(scanner.nextLine());
            if (managerChoice < 1 || managerChoice > managerList.size()){
                System.out.println( "Error: invalid manager choice!");
                return;
            }
        } catch ( NumberFormatException e){
            System.out.println("Invalid Input!");
            return;
        }
        String selectedManagerType = managerList.get(managerChoice - 1);

        //extra info
        System.out.println("Please, enter your email: ");
        String email = scanner.nextLine(). trim();

        System.out.println("Enter your Job Title: ");
        String jobTitle = scanner.nextLine(). trim();

        System.out.println("Enter your Company Name: ");
        String company = scanner.nextLine(). trim();

        System.out.println("Enter yor Salary: ");
        double salary = 0;
        try{
            salary = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid Input!");
            return;
        }

        //create and add a new employee if needed
        Employee newEmployee = new Employee(firstName, lastName, email, salary, selectedDepartment, selectedManagerType, jobTitle, company);

        employees.add(newEmployee);

        System.out.println("Employee added successfully!");
        System.out.println(newEmployee);
    }

    //it should display all the employees
    public static void displayAllEmployees(List<Employee> employees){
        System.out.println("\n All Employees currently in the company: ");
        for (int i = 0; i < employees.size(); i++){
            System.out.println((i + 1) + ".  " + employees.get(i));
        }
    }

}
