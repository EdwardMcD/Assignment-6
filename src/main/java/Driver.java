import Assignment6View.BankApplicationMain;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        try {
            new BankApplicationMain();
        } catch (IOException ioe) {
            System.out.println("Exception caught opening landing page: " + ioe.getMessage());
        }
    }
}