package view;

import utils.MenuEnum;
import java.util.Scanner;

public class BankUI {
    private Scanner scanner;

    public BankUI() {
        this.scanner = new Scanner(System.in);
    }

    //welcome menu is set here
    public void displayWelcome() {
        System.out.println("===================================================");
        System.out.println("         KBANK EMPLOYEE MANAGEMENT SYSTEM         ");
        System.out.println("===================================================");
    }

    //prompt for filename
    public String getFileName() {
        System.out.println("Please enter the file name: ");
        return scanner.nextLine().trim();
    }

    // display menu and parse choice
    public MenuEnum getMenuChoice() {
        MenuEnum.displayMenu();
        System.out.print("\nEnter your choice: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return MenuEnum.fromChoice(choice);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return null;
        }
    }

    //prompt for a search name
    public String getSearchName() {
        System.out.println("Please enter employee name to search: ");
        return scanner.nextLine().trim();
    }

//ask how many records to use for the binary code tree
    public int getNumberOfRecords(int maxAvailable) {
        System.out.println("Please enter the number of employees to insert into tree (min 20, max " + maxAvailable + "): ");
        try {
            int number =  Integer.parseInt(scanner.nextLine().trim());
            if (number < 20){
                System.out.println("Minimum 20 records required!");
                return -1;
            }
            if (number > maxAvailable){
                System.out.println("Maximum available records exceeded!");
                return -1;
            }
            return number;
        }catch (NumberFormatException e){
            System.out.println("Invalid input! Please enter a number.");
            return -1;
        }
    }

    public Scanner getScanner() {
        return scanner;
    }
    public void close(){ scanner.close();}
}
