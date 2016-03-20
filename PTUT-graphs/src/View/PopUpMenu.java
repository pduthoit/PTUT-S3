/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author User
 */
public class PopUpMenu extends JPopupMenu {
    private JMenuItem item;
    public PopUpMenu(){
//        ActionListener menuListener = new ActionListener() {
//        public void actionPerformed(ActionEvent event) {
//            System.out.println("Popup menu item ["
//                    + event.getActionCommand() + "] was pressed.");
//        }
//        };
        
        this.add(item = new JMenuItem("Supprimer le noeud"));
        this.addSeparator();
        this.add(item = new JMenuItem("fzfzf"));
//        item.addActionListener(menuListener);
    }

    void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            this.show(e.getComponent(),e.getX(), e.getY());
        }
    }
}
