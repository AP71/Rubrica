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
    private JButton registerButton;

    public Login() {
        super("Login");
        init(LARGHEZZA, ALTEZZA);
        initListener();
    }

    private void initListener() {
        loginButton.addActionListener(e ->  {
            String res = Utente.getInstance().tryLogin(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()));
            if (res == null) {
                Data.getInstance().loadContact();
                new Rubrica("Rubrica");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Errore. "+res,"Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e ->  {
            String res = Utente.getInstance().register(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()));
            if (res.equals("")) {
                JOptionPane.showMessageDialog(this, "Registrazione effettuata con successo.","Registrazione", JOptionPane.OK_OPTION);
            } else {
                if (res.contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "Errore: impossibile registrarsi con il seguente username, utente già presente.","Registrazione", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Errore. "+res,"Registrazione", JOptionPane.ERROR_MESSAGE);
                }

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
