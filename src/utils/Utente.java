package utils;

public class Utente {

    private String username = "utente1";

    private String password = "testerRubrica-2024";

    public Utente() {

    }

    public boolean tryLogin(String username, String password) {
        System.out.println(username + " "+ password);
        return (this.username.equals(username) && this.password.equals(password));
    }

}
