package utils;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.sql.*;
import java.util.Properties;

public class Data {
    private static Data data;
    private final String path = "./credenziali_database.properties";
    private Connection connection = null;
    private String url;
    private String username;
    private String password;
    private String porta;
    private static Users utenti = Users.getInstance();

    private void loadProp() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(path));
        url = prop.getProperty("ip-server-mysql");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
        porta = prop.getProperty("porta");
    }

    private Data() throws Exception{
        this.loadProp();
        this.connection = DriverManager.getConnection("jdbc:mysql://"+url+":"+porta+"/rubrica", username, password);
    }

    public static Data getInstance(){
        if (data == null) {
            try {
                data = new Data();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Errore di connessione con il database. "+e.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return data;
    }

    public String registerUser(String username, String pass) {
        try {
            Statement stmt = this.connection.createStatement();
            byte[] salt = Cipher.genSalt();
            String sql = "insert into utenti(username,password,salt) values (?,?,?);";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, new BigInteger(1, Cipher.encrypt(salt, pass)).toString(16));
            prep.setString(3, new String(salt));
            prep.execute();
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String tryLogin(String username, String pass) {
        try {
            Statement stmt = this.connection.createStatement();
            byte[] salt = null;
            String user = null;

            //Get salt from db table
            ResultSet rs = stmt.executeQuery("select salt from utenti where username='"+username+"';");
            while(rs.next()) {
                salt = rs.getString("salt").getBytes();
            }
            if (salt==null) return "Username e/o password errati.";

            //Get salt from db table
            String pwd = new BigInteger(1, Cipher.encrypt(salt,pass)).toString(16);
            rs = stmt.executeQuery("select username from utenti where password='"+pwd+"';");
            while(rs.next()) {
                user = rs.getString("username");
            }
            if (user != null && user.equals(username)) return null;
            else {
                return "Username e/o password errati.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void loadContact() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select id, nome, cognome, indirizzo, telefono, eta from contatti where utenti_username='"+Utente.getInstance().getUsername()+"';");
            while(rs.next()) {
                utenti.addUser(new Persona(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getInt("eta")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContact(Persona persona) {
        try {
            String sql = "insert into contatti(nome, cognome, indirizzo, telefono, eta, utenti_username) values (?,?,?,?,?,?);";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, persona.getNome());
            prep.setString(2, persona.getCognome());
            prep.setString(3, persona.getIndirizzo());
            prep.setString(4, persona.getTelefono());
            prep.setInt(5, persona.getEta());
            prep.setString(6, Utente.getInstance().getUsername());
            prep.execute();

            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("select LAST_INSERT_ID()");
            while(rs.next()) {
                persona.setId(rs.getInt(1));
            }
            loadContact();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Persona persona) {
        try {
            Statement stmt = this.connection.createStatement();
            byte[] salt = Cipher.genSalt();
            String sql = "update contatti set nome=?, cognome=?, indirizzo=?, telefono=?, eta=? where id=?;";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, persona.getNome());
            prep.setString(2, persona.getCognome());
            prep.setString(3, persona.getIndirizzo());
            prep.setString(4, persona.getTelefono());
            prep.setInt(5, persona.getEta());
            prep.setInt(6, persona.getId());
            prep.execute();
            loadContact();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Persona p) {
        try {
            Statement stmt = this.connection.createStatement();
            byte[] salt = Cipher.genSalt();
            String sql = "delete from contatti where id=?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setInt(1, p.getId());
            prep.execute();
            utenti.deleteUser(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public TableModel getUsers() { return new TableModel(utenti); }
}
