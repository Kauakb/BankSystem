import view.BankUI;
import controller.BankController;


// When prompted for the file name, enter: Applicants_Form.txt

public class Main {
    public static void main(String[] args) {
        BankUI view = new BankUI();
        BankController controller = new BankController(view);
        controller.run();
    }
}