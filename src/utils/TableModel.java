package utils;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private Users users;
    private String[] columnNames = {"Nome", "Cognome", "Telefono"};

    public TableModel(Users users) {
        this.users = users;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return users.getLenght();
    }

    @Override
    public int getColumnCount() {
        return 3;
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
