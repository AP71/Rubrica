package interfaces;

import utils.Data;
import utils.Persona;
import utils.Users;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JFrame {

    private static int LARGHEZZA = 600;
    private static int ALTEZZA = 300;
    private JPanel editorPersona;
    private JPanel buttonPanel;
    private JButton annullaButton;
    private JButton salvaButton;
    private JTextField textFieldNome;
    private JTextField textFieldEta;
    private JTextField textFieldIndirizzo;
    private JTextField textFieldCognome;
    private JTextField textFieldTelefono;
    private JScrollPane scrollPane;
    private Rubrica rubrica;

    private Persona persona;

    public Editor(Rubrica rubrica) {
        super("editor-persona");
        this.rubrica = rubrica;
        init(LARGHEZZA, ALTEZZA);
        initListener();
    }

    public Editor(Rubrica rubrica, Persona persona) {
        super("editor-persona");
        this.rubrica = rubrica;
        init(LARGHEZZA, ALTEZZA);
        initListener();
        this.persona = persona;
        if (this.persona != null) {
            textFieldNome.setText(persona.getNome());
            textFieldCognome.setText(persona.getCognome());
            textFieldIndirizzo.setText(persona.getIndirizzo());
            textFieldTelefono.setText(persona.getTelefono());
            textFieldEta.setText(persona.getEta().toString());
        }
    }

    private void initListener() {
        salvaButton.addActionListener(e -> {
            try {
                String nome = textFieldNome.getText();
                String cognome = textFieldCognome.getText();
                String indirizzo = textFieldIndirizzo.getText();
                String telefono = textFieldTelefono.getText();
                int eta = Integer.parseInt(textFieldEta.getText());
                if (persona == null) {
                    Data.getInstance().addContact(new Persona(nome, cognome, indirizzo, telefono, eta));
                    rubrica.reloadData();
                    dispose();
                } else {
                    persona.setNome(nome);
                    persona.setCognome(cognome);
                    persona.setIndirizzo(indirizzo);
                    persona.setTelefono(telefono);
                    persona.setEta(eta);
                    Data.getInstance().updateContact(persona);
                    rubrica.reloadData();
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        annullaButton.addActionListener(e -> dispose());
    }

    private void init(int larghezza, int lunghezza) {
        this.setContentPane(editorPersona);
        this.setSize(larghezza, lunghezza);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
