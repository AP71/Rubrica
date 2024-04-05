package interfaces;

import utils.Data;


public class Main {

    private static Data data;

    public static void main(String[] args) {
        data = Data.getInstance();
        if (data == null) {
            return;
        }
        new Login();
    }

}
