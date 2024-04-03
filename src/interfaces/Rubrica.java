package interfaces;

import Listners.ButtonListener;
import utils.Persona;
import utils.TableModel;
import utils.Users;

import javax.swing.*;
import java.awt.*;

public class Rubrica extends JFrame {
    final int LARGHEZZA = 800;
    final int LUNGHEZZA = 400;
    private JPanel homePanel;
    private JTable table;
    private JButton nuovoButton;
    private JButton modificaButton;
    private JButton eliminaButton;
    private JPanel buttonsPanel;

    private static Users utenti = Users.getInstance();

    public Rubrica(String titolo) {
        super(titolo);
        init(LARGHEZZA, LUNGHEZZA);
        initListener();
    }

    private void initListener() {
        nuovoButton.addActionListener(new ButtonListener());
        modificaButton.addActionListener(new ButtonListener());
        eliminaButton.addActionListener(new ButtonListener());
    }

    private void init(int larghezza, int lunghezza) {
        this.setSize(larghezza,lunghezza);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Pannello tabella
        table = new JTable();
        table.setModel(new TableModel(utenti));
        homePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        homePanel.add(table);

        //Pannello bottoni
        nuovoButton = new JButton("Nuovo");
        modificaButton = new JButton("Modifica");
        eliminaButton = new JButton("Elimina");
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));
        buttonsPanel.add(nuovoButton);
        buttonsPanel.add(modificaButton);
        buttonsPanel.add(eliminaButton);

        this.getContentPane().add(homePanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

}
