package interfaces;

import utils.Data;
import utils.Utente;

import javax.swing.*;
import java.util.Arrays;

public class Login extends JFrame{

    private static int LARGHEZZA = 1000;
    private static int ALTEZZA = 300;
    private JPanel loginPanel;
    private JPanel buttonPanel;
    private JButton loginButton;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;

    private static Utente utente = new Utente();

    public Login() {
        super("Login");
        init(LARGHEZZA, ALTEZZA);
        initListener();
    }

    private void initListener() {
        loginButton.addActionListener(e ->  {
            boolean res = utente.tryLogin(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()));
            if (res) {
                Data.loadData();
                new Rubrica("Rubrica");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login errato.","Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void init(int larghezza, int lunghezza) {
        this.setContentPane(loginPanel);
        this.setSize(larghezza,lunghezza);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
