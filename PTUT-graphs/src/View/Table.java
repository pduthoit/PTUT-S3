/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import Model.ModeleTable;

/**
 *
 * @author User
 */
public class Table {
    private JScrollPane scrollerTable = new JScrollPane();
    private JTable table = new JTable();
    private ModeleTable modele;
    
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
    
    /*private class ViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            /*if (command.equals(STANDARD)) {
                gui.sciPanelSetVisible(false);
                standardView.setEnabled(false);
                scientificView.setEnabled(true);
            } else if (command.equals(SCIENTIFIC)) {
                gui.sciPanelSetVisible(true);
                standardView.setEnabled(true);
                scientificView.setEnabled(false);
            }
        }
    }*/

}
