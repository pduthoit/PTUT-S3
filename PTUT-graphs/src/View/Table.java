/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import Model.ModeleTable;
import static View.SwingContainer.modele;

/**
 *
 * @author User
 */
public class Table {
    private JScrollPane scrollerTable = new JScrollPane();
    private JTable table = new JTable();
    
    public Table(){
        modele = new ModeleTable();
        
        table.setModel(modele);
        table.setColumnSelectionAllowed(true);
        scrollerTable.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    
    public JScrollPane getJScrollPane() {
        return scrollerTable;
    }
    
    public void updateData(){
        table.setModel(modele);
    }
    
}
