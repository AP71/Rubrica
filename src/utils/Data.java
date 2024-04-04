package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Data {

    private static String path= "./informazioni/";
    private static Users utenti = Users.getInstance();

    public static void loadData() {
        String newLine;
        String[] value;
        Persona newUser;
        Scanner scanner;
        try {
            if (!Files.exists(Path.of(path))) throw new FileNotFoundException();
            else {
                File dir = new File(path);
                if (dir.isDirectory() && dir.list().length > 0) {
                    for (String file: dir.list()) {
                        scanner =  new Scanner(new File(path+file));
                        while(scanner.hasNextLine()) {
                            newLine = scanner.nextLine();
                            value = newLine.split(";");
                            if (value.length == 5) {
                                newUser = new Persona(value[0], value[1], value[2], value[3], Integer.parseInt(value[4]));
                                newUser.setFileName(path+file.replaceFirst("[.][^.]+$", ""));
                                utenti.addUser(newUser);
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException notFound) {
            try {
                File dir = new File(path);
                dir.mkdir();
            } catch (Exception notFoundException) {
                notFoundException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createAndWrite(Persona p, String filename) throws FileNotFoundException {
        PrintStream stream = new PrintStream(filename);
        stream.println(String.join(";", p.getNome(), p.getCognome(), p.getIndirizzo(), p.getTelefono(), p.getEta().toString()));
        stream.flush();
        stream.close();
    }

    public static void storeData(Persona p) {
        try {
            if (p.getFileName() == null)
            {
                p.setFileName(path+p.getNome()+"-"+p.getCognome());
                if (Files.notExists(Path.of(p.getFileName().concat(".txt")))) {
                    createAndWrite(p, p.getFileName().concat(".txt"));
                } else {
                    String newFileName;
                    int i = 1;
                    while (true) {
                        newFileName = p.getFileName().concat("("+i+").txt");
                        if (Files.notExists(Path.of(newFileName))) {
                            createAndWrite(p, newFileName);
                            break;
                        } else i += 1;
                    }
                }
            } else {
                createAndWrite(p, p.getFileName().concat(".txt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(Persona persona) {
        utenti.addUser(persona);
        storeData(persona);
    }

    public static void update(Persona persona) {
        utenti.update(persona);
        storeData(persona);
    }

    public static void deleteUser(Persona p) {
        try {
            utenti.deleteUser(p);
            File file = new File(p.getFileName().concat(".txt"));
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static TableModel getUsers() { return new TableModel(utenti); }
}
