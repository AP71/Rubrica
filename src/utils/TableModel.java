package utils;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private Users<Persona> users;
    private String[] header = {"Nome", "Cognome", "Telefono"};

    public TableModel(Users<Persona> users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    @Override
    public int getRowCount() {
        return users.getLenght();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = users.getUsers().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getNome();
            case 1:
                return p.getCognome();
            case 2:
                return p.getTelefono();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

}
