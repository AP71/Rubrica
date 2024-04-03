package utils;

import java.util.ArrayList;
import java.util.List;

public class Users<Persona> {

    private static Users instance;
    private List<Persona> users;

    private Users() {
        this.users = new ArrayList<>();
    }

    public static Users getInstance() {
        if (instance == null) instance = new Users();
        return instance;
    }

    public List<Persona> getUsers() { return users;}

    public void addUser(Persona newUser) {
        this.users.add(newUser);
    }

    public int getLenght() { return users.size();}

    public void deleteUser() {
        //TODO
    }


}
