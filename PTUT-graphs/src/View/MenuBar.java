/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Controller.Sommet;
import Model.ModeleTable;
import static View.SwingContainer.myWindow;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.graphstream.graph.Graph;

/**
 *
 * @author User
 */
public class MenuBar{

    final private JMenuBar menuBar = new JMenuBar();
    final private JMenu menu0 = new JMenu("Fichier");
    final private JMenu menu1 = new JMenu("Edition");
    final private JMenu menu2 = new JMenu("Aide");
    final private JMenuItem menu0Item0 = new JMenuItem("Ouvrir");
    final private JMenuItem menu1Item0 = new JMenuItem("Créer un sommet");

    private SwingContainer gui;

    private ModeleTable modele;
 
    public MenuBar(){
        GraphPanel gPanel = myWindow.getGraphPanel();
        System.out.println(gPanel);

        // Add a shortcut 
        menu0Item0.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFileChooser dialogue = new JFileChooser(new File("."));
                File fichier;
                if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fichier = dialogue.getSelectedFile();
                    gPanel.setGraphFile(fichier.getAbsolutePath());
                }
            }
        });
        menu0Item0.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));

        menu0.add(menu0Item0);
        menu1.add(menu1Item0);
        menuBar.add(menu0);
        menuBar.add(menu1);
        menuBar.add(menu2);

    }

    public JMenuBar getJMenuBar() {
        return menuBar;
    }

    /*private class ViewAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals(STANDARD)) {
                gui.sciPanelSetVisible(false);
                standardView.setEnabled(false);
                scientificView.setEnabled(true);
            } else if (command.equals(SCIENTIFIC)) {
                gui.sciPanelSetVisible(true);
                standardView.setEnabled(true);
                scientificView.setEnabled(false);
            }
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }*/
    /*private void menu1Item0ActionPerformed(java.awt.event.ActionEvent evt) {
        List<Sommet> listeSommets = myWindow.getGraphPanel().getListeSommets();
        Graph g = myWindow.getG();
        JPanel myPanel = new JPanel();
        JTextField sommet = new JTextField(5);

        boolean test = false;

        myPanel.add(new JLabel("Nom du sommet :"));
        myPanel.add(sommet);
        int result;
        do {
            result = JOptionPane.showConfirmDialog(null, myPanel, "Saisir le nom du sommet à ajouter", JOptionPane.OK_CANCEL_OPTION);

            for (int i = 0; i < listeSommets.size(); i++) {
                if (listeSommets.get(i).getNom().equals(sommet.getText())) {
                    test = true;
                } else {
                    test = false;
                }
            }

        } while (test && result == JOptionPane.OK_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            GraphPanel graphPanel = myWindow.getGraphPanel();
            String s = sommet.getText();
            g.addNode(s);
            g.getNode(s).setAttribute("ui.label", s);

            listeSommets.add(new Sommet(s, g.getNode(s).getDegree()));
            gui.updateTable();
        }
        sommet.requestFocus();

    }*/
}
