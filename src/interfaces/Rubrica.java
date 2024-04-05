package interfaces;

import utils.Data;
import utils.Persona;
import utils.Users;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rubrica extends JFrame {

    private static int LARGHEZZA = 1000;
    private static int ALTEZZA = 500;
    private JPanel homePanel;
    private JButton nuovoButton;
    private JButton modificaButton;
    private JButton eliminaButton;
    private JPanel gridPanel;
    private JPanel bottomPanel;
    private JTable table;
    private JScrollPane scrollPane;
    private Rubrica self;

    public Rubrica(String titolo) {
        super(titolo);
        self = this;
        init(LARGHEZZA, ALTEZZA);
        initListener();
        table.setModel(Data.getInstance().getUsers());
        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 18));
    }

    private void initListener() {
        nuovoButton.addActionListener(e -> new Editor(self));
        modificaButton.addActionListener(e -> {
            if (Users.getInstance().getSelectedUser() == null) {
                JOptionPane.showMessageDialog(self, "Per effettuare una modifica è necessario selezionare un utente.","Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                new Editor(self, Users.getInstance().getSelectedUser());
            }
        });
        eliminaButton.addActionListener(e -> {
            Persona user = Users.getInstance().getSelectedUser();
            if (user == null) {
                JOptionPane.showMessageDialog(self, "Per effettuare una cancellazione è necessario selezionare un utente.","Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                int res = JOptionPane.showConfirmDialog(self, "Eliminare la persona: ".concat(user.getNome()).concat(" ").concat(user.getCognome()).concat("?"), "Conferma cancellazione", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    Data.getInstance().deleteContact(user);
                    reloadData();
                }
            }
        });
        table.getSelectionModel().addListSelectionListener(e -> Users.getInstance().setSelectedUser(table.getSelectedRow()));

    }

    private void init(int larghezza, int lunghezza) {
        this.setContentPane(homePanel);
        this.setSize(larghezza,lunghezza);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void reloadData() { table.setModel(Data.getInstance().getUsers());}

}
