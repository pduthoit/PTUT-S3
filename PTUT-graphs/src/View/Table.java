package View;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import Model.ModeleTable;
import static View.SwingContainer.modele;


public class Table {

    private JScrollPane scrollerTable = new JScrollPane();
    private JTable table = new JTable();

    public Table() {
        modele = new ModeleTable();

        table.setModel(modele);
        table.setColumnSelectionAllowed(true);
        scrollerTable.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    public JScrollPane getJScrollPane() {
        return scrollerTable;
    }

    public void updateData() {
        table.setModel(modele);
    }

}
