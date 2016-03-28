/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.File;
import javax.swing.JFileChooser;

import static View.SwingContainer.myWindow;
import static View.SwingContainer.dynFuncIsRunning;

/**
 *
 * @author User
 */
public class MenuBar {

    final private JMenuBar menuBar = new JMenuBar();
    final private JMenu menu0 = new JMenu("Fichier");
    final private JMenu menu1 = new JMenu("Edition");
    final private JMenu menu2 = new JMenu("Fonctions de dynamique");
    final private JMenu menu3 = new JMenu("Aide");
    
    final private JMenuItem menu0Item0 = new JMenuItem("Ouvrir");
    final private JMenuItem menu1Item0 = new JMenuItem("Créer un sommet");
    final private JMenuItem menu2Item0 = new JMenuItem("Suppression aléatoire de noeuds");
    final private JMenuItem menu2Item1 = new JMenuItem("Suppression aléatoire d'arrêtes");
    final private JMenuItem menu2Item2 = new JMenuItem("Arrêter");
    
    final private JMenu menu2graphique = new JMenu("Graphique d'analyse");
    final private JMenuItem menu2graphiqueItem0 = new JMenuItem("Nombre de noeuds et connexité");
    final private JMenuItem menu2graphiqueItem1 = new JMenuItem("Nombre de composantes connexes");


    public MenuBar() {
        final GraphPanel gPanel = myWindow.getGraphPanel();
        System.out.println(gPanel);

        // Add a shortcut 
        menu0Item0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFileChooser dialogue = new JFileChooser(new File("./src/GraphFiles/"));
                File fichier;
                if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    fichier = dialogue.getSelectedFile();
                    gPanel.setGraphFile(fichier.getAbsolutePath());
                }
            }
        });
        menu0Item0.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        
        menu1Item0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myWindow.getGraphPanel().createGraphNode();
            }
        });
        
        menu2Item0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myWindow.getGraphPanel().deleteRandomNode();
            }
        });
        
        menu2Item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myWindow.getGraphPanel().deleteRandomEdge();
            }
        });
        
        menu2Item2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dynFuncIsRunning = false;
            }
        });
        
        menu2graphiqueItem0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myWindow.getSplitPanelSouth().setLeftComponent(myWindow.getBarChart().getChart());
            }
        });
        
        menu2graphiqueItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myWindow.getSplitPanelSouth().setLeftComponent(myWindow.getCCChart().getChart());
            }
        });
        
        
        menu2Item2.setEnabled(false);
        menu0.add(menu0Item0);
        menu1.add(menu1Item0);
        
        menu2graphique.add(menu2graphiqueItem0);
        menu2graphique.add(menu2graphiqueItem1);
        menu2.add(menu2graphique);
        menu2.addSeparator();
        menu2.add(menu2Item0);
        menu2.add(menu2Item1);
        menu2.addSeparator();
        menu2.add(menu2Item2);
        
        menuBar.add(menu0);
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

    }

    public JMenuBar getJMenuBar() {
        return menuBar;
    }
    
    public void setMenu2Item2Clickable(boolean t) {
        menu2Item2.setEnabled(t);
    }
}
