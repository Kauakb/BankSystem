package utils;

import model.Employee;

import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithm {

    // Merge Sort - Recursive Algorithm
    public static List<Employee> mergeSortByName(List<Employee> employees) {
        if (employees == null || employees.size() <= 1) {
            return employees;
        }

        return mergeSort(new ArrayList<>(employees), 0, employees.size() - 1);
    }

    private static List<Employee> mergeSort(List<Employee> arr, int left, int right) {
        // single element is already sorted
        if (left >= right) {
            List<Employee> result = new ArrayList<>();
            result.add(arr.get(left));
            return result;
        }

        // find middle point
        int mid = left + (right - left) / 2;

        //  sort left and right
        List<Employee> leftSorted = mergeSort(arr, left, mid);
        List<Employee> rightSorted = mergeSort(arr, mid + 1, right);

        //  merge the sorted halves
        return merge(leftSorted, rightSorted);
    }

    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> result = new ArrayList<>();
        int i = 0, j = 0;

        // Compare names and add smaller to result
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getFullName().compareToIgnoreCase(right.get(j).getFullName()) <= 0) {
                result. add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        // Add remaining elements
        while (i < left.size()) {
            result. add(left.get(i++));
        }
        while (j < right.size()) {
            result.add(right. get(j++));
        }

        return result;
    }

    // Display first 20 names
    public static void displayFirst20(List<Employee> employees) {
        System.out. println("\n=== Sorted Employees (First 20) ===");
        int limit = Math.min(20, employees.size());
        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) + ".  " + employees.get(i). getFullName() +
                    " - " + employees.get(i).getDepartment() +
                    " - " + employees.get(i).getManagerType());
        }
    }
}