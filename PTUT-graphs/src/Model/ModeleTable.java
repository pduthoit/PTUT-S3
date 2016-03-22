/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */

public class ModeleTable extends AbstractTableModel{
    private String[] nomColonnes = {"Sommet", "Sommet(s) adjacent(s)", "Degré",};


    @Override
    public String getColumnName(int column) {
        return nomColonnes[column]; 
    }
    private List<Object[]> data;

    public ModeleTable() {
        super();
        data =  new ArrayList<>();
    }
            
     @Override
    public int getRowCount() {
        return data.size();
    }

     @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

     @Override
    public Object getValueAt(int row, int col) {
        Object[] ligne =  data.get(row);
       
        return ligne[col];
    }
    
     public Object[] getLigne(int i) {
       
        return data.get(i);
    }

    public void addRow(Object[] row) {
        data.add(row);
        this.fireTableDataChanged();
    }
    
    public void supprimeLigne(int ligne){
      
       data.remove(ligne);
       this.fireTableDataChanged();
        
    }
    
    public void supprimeToutesLesLigne(){
      
       data.removeAll(data);
       this.fireTableDataChanged();
        
    }
    
     public List<Object[]> getData() {
        return data;     
    }
}
