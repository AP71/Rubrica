package interfaces;

import utils.Data;

public class Main {

    public static void main(String[] args) {
        Data.loadData();
        new Rubrica("Rubrica");
    }

}
