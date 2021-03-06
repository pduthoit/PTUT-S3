/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.SwingContainer.myWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.graphstream.ui.graphicGraph.GraphicElement;

public class PopUpMenu extends JPopupMenu {

    private JMenuItem item;

    public PopUpMenu(final GraphicElement n, Boolean estUnNoeud) {

        // créé les ActionListener
        ActionListener removeNode = new ActionListener() { // remove node
            public void actionPerformed(ActionEvent event) {
                myWindow.getGraphPanel().removeGraphNode((String) n.getId());
            }
        };

        ActionListener createNode = new ActionListener() { // create node
            public void actionPerformed(ActionEvent event) {
                myWindow.getGraphPanel().createGraphNode();
            }
        };

        ActionListener createEdge = new ActionListener() { // create edge
            public void actionPerformed(ActionEvent event) {
                myWindow.getGraphPanel().createGraphEdge();
            }
        };

        if (estUnNoeud) { // si clic sur un noeud
            this.add(item = new JMenuItem(n.getId()));
            this.addSeparator();
            this.add(item = new JMenuItem("Supprimer le noeud"));
            item.addActionListener(removeNode);
            this.addSeparator();
            this.add(item = new JMenuItem("Ajouter une arrête"));
            item.addActionListener(createEdge);
        } else if (!estUnNoeud) { // si clic dans le vide
            this.add(item = new JMenuItem("Créer un noeud"));
            item.addActionListener(createNode);
            this.addSeparator();
            this.add(item = new JMenuItem("Ajouter une arrête"));
            item.addActionListener(createEdge);
        }
    }

    void showPopup(MouseEvent e) {
        this.show(e.getComponent(), e.getX(), e.getY());
    }
}
