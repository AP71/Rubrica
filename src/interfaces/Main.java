package interfaces;

import utils.Data;


public class Main {

    private static Data data;

    public static void main(String[] args) {
        if (Data.getInstance() == null) {
            return;
        }
        new Login();
    }

}
