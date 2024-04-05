package utils;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Data {
    private static Data data;
    private final String path = "./database/credenziali_database.properties";
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
            } catch (SQLSyntaxErrorException sql) {
                JOptionPane.showMessageDialog(null, "Errore di connessione con il database. "+sql.getMessage(),"Errore", JOptionPane.ERROR_MESSAGE);
                return null;
            }catch (Exception e) {
                throw new RuntimeException(e);
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

    }

    public void addContact(Persona persona) {

    }

    public void updateContact(Persona persona) {

    }

    public void deleteContact(Persona p) {

    }
    public TableModel getUsers() { return new TableModel(utenti); }
}
