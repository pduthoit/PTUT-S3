package View;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
    
    private InfoGraphPanel infoGraphPanel;
    
    public InfoPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        infoGraphPanel = new InfoGraphPanel();
        
        this.add(infoGraphPanel);
        
        Table table = new Table();
        
        this.add(table.getJScrollPane());
    }
    
    public InfoGraphPanel getInfoGraphPanel() {
        return infoGraphPanel;
    }
    
}
