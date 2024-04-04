package utils;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private static Users instance;
    private List<Persona> users;
    private int selectedUser = -1;

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

    public void deleteUser(Persona persona) {
        users.remove(persona);
    }

    public void setSelectedUser(int row) { this.selectedUser = row;}

    public Persona getSelectedUser() {
        if (this.selectedUser <= -1) return null;
        return users.get(selectedUser);
    }

    public void update(Persona persona) {
        int index = users.indexOf(persona);
        users.set(index, persona);
    }

}
