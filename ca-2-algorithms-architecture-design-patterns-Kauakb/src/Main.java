import view.BankUI;
import controller.BankController;


//Applicants_Form.txt - password

public class Main {
    public static void main(String[] args) {
        BankUI view = new BankUI();
        BankController controller = new BankController(view);
        controller.run();
    }
}