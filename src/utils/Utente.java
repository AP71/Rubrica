package utils;

public class Utente {

    private static Utente utente;

    private static String username;

    private Utente() {

    }

    public static Utente getInstance(){
        if (utente == null) utente = new Utente();
        return utente;
    }

    public String tryLogin(String username, String password) {
        String res = Data.getInstance().tryLogin(username, password);
        System.out.println(res);
        if (res == null) {
            this.username = username;
            return null;
        } else {
            return res;
        }
    }

    public String register(String username, String password) {
        String res = Data.getInstance().registerUser(username, password);
        if (res != null) {
            return res;
        }
        return "";
    }

    public String getUsername() {
        return username;
    }
}
