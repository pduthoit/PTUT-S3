/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.SwingContainer.myWindow;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import org.graphstream.ui.graphicGraph.GraphicElement;

/**
 *
 * @author User
 */
public class PopUpMenu extends JPopupMenu {
    private JMenuItem item;
    public PopUpMenu(final GraphicElement n,Boolean estUnNoeud){
        ActionListener removeNode = new ActionListener() {
        public void actionPerformed(ActionEvent event) {           
            myWindow.getGraphPanel().removeGraphNode((String)n.getId());
        }
        };
        
        ActionListener createNode = new ActionListener() {
        public void actionPerformed(ActionEvent event) {           
            System.out.println("heyya");
            myWindow.getGraphPanel().createGraphNode();
        }
        };
        

        ActionListener createEdge = new ActionListener() {
        public void actionPerformed(ActionEvent event) {           
            System.out.println("heyya");
            myWindow.getGraphPanel().createGraphEdge();
        }
        };
        
        if(estUnNoeud){
            this.add(item = new JMenuItem("Supprimer le noeud"));
            item.addActionListener(removeNode);
            this.addSeparator();
            this.add(item = new JMenuItem("Ajouter une arrête"));
            item.addActionListener(createEdge);
//            this.addSeparator();
//            this.add(item = new JMenuItem("fzfzf"));
        }else if(!estUnNoeud){
            this.add(item = new JMenuItem("Créer un noeud"));
            item.addActionListener(createNode);
            this.addSeparator();
            this.add(item = new JMenuItem("Ajouter une arrête"));
            item.addActionListener(createEdge);
//            this.addSeparator();
//            this.add(item = new JMenuItem("fzfzf"));
        }
        
    }

    void showPopup(MouseEvent e) {
        this.show(e.getComponent(),e.getX(), e.getY());
    }
}
