package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
    
    
    public InfoPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        
        
        Table table = new Table();
        
        this.add(table.getJScrollPane());
    }
    
    
}
