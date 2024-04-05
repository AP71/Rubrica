package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        for (Persona p: users) {
            if (Objects.equals(p.getId(), newUser.getId())) return;
        }
        users.add(newUser);
    }

    public static void reset() {
        instance.users.clear();
        instance.selectedUser = -1;
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


}
