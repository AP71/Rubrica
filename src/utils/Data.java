package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Data {

    private static String fileName = "./data/Informazioni.txt";
    private static Users utenti = Users.getInstance();

    public static void loadData() {
        File file = new File(fileName);
        String newLine;
        String[] value;
        Persona newUser;
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                newLine = scanner.nextLine();
                value = newLine.split(";");
                if (value.length == 5) {
                    newUser = new Persona(value[0], value[1], value[2], value[3], Integer.parseInt(value[4]));
                    utenti.addUser(newUser);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                File newFile = new File(fileName);
                new File("./data/").mkdir();
                newFile.createNewFile();
            } catch (Exception createFileException) {
                createFileException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void storeData() {
        try {
            PrintStream stream = new PrintStream(fileName);
            for (Persona p : utenti.getUsers()) {
                stream.println(String.join(";", p.getNome(), p.getCognome(), p.getIndirizzo(), p.getTelefono(), p.getEta().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Persona persona) {
        utenti.addUser(persona);
        storeData();
    }

    public static void update(Persona persona) {
        utenti.update(persona);
        storeData();
    }

    public static void deleteUser(Persona persona) {
        utenti.deleteUser(persona);
        storeData();
    }
    public static TableModel getUsers() { return new TableModel(utenti); }
}
