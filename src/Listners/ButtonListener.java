package Listners;

import utils.Persona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sorgente = (JButton) e.getSource();

        if (sorgente.getActionCommand().equals("Nuovo")) {
            System.out.println("Nuovo");
        } else if (sorgente.getActionCommand().equals("Modifica")) {
            System.out.println("Modifica");
        } else {
            System.out.println("Elimina");
        }
    }
}
