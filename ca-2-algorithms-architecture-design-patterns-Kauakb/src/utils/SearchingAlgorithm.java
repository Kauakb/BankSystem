package utils;
import model.Employee;
import java.util.List;

// searching utility implementing binary search on a sorted list.
//it should assume if list is sorted by full name

public class SearchingAlgorithm {

    //public search entry by returning match employee or null
    public static Employee binarySearchByName(List<Employee> employees, String searchName){
        if (employees == null || employees.isEmpty()) {
            return null;
        }
        return binarySearch(employees, searchName, 0, employees.size() - 1);
    }

    private static Employee binarySearch(List<Employee> arr, String searchName, int left, int right) {
        //in case if element was not found
        if (left > right){
                return null;
            }

        //finds the middle index
        int mid = left + (right - left) / 2;
        Employee midEmployee = arr.get(mid);

        //System.out.println("DEBUG: Checking index " + mid + ": '" + midEmployee.getFullName() + "'");

        //compare names
        int comparison = midEmployee.getFullName().compareToIgnoreCase(searchName);
            if (comparison == 0) {
                //it finds the employee
            return midEmployee;
        } else if (comparison < 0) {
                return binarySearch(arr, searchName, mid + 1, right);
            } else {
                return binarySearch(arr, searchName, left, mid - 1);
            }
    }

    //search and display employee details
    public static void searchAndDisplay(List<Employee> employees, String searchName) {
        List<Employee> sortedEmployees = SortingAlgorithm.mergeSortByName(employees);

//        // DEBUG: Check if the employee exists in the list at all
//        System.out.println("DEBUG: Searching for: '" + searchName + "'");
//
//        // Show indices 10-20 to see the order
//        System.out.println("DEBUG: Employees at indices 10-20:");
//        for (int i = 10; i <= Math.min(20, sortedEmployees.size() - 1); i++) {
//            System.out.println("  Index " + i + ": '" + sortedEmployees.get(i).getFullName() + "'");
//        }

        // Check if the employee exists in the list
        for (int i = 0; i < sortedEmployees.size(); i++) {
            if (sortedEmployees.get(i).getFullName().equalsIgnoreCase(searchName)) {
                System.out.println("DEBUG: Found '" + searchName + "' at index " + i);
                break;
            }
        }

        Employee found = binarySearchByName(sortedEmployees, searchName);

        if (found != null) {
            System.out.println("\n=== Employee Found ===");
            System.out.println("Name: " + found.getFullName() + " found!");
            System.out.println("Manager Type: " + found.getManagerType());
            System.out.println("Department: " + found.getDepartment());
            System.out.println("Job Title: " + found.getJobTitle());
            System.out.println("Salary: €" + found.getSalary());
            System.out.println("Company: " + found.getCompany());
        } else {
            System.out.println("\n Employee, " + searchName + "'s was not found in the system.");
        }
    }
}