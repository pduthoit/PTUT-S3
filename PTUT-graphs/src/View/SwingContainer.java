package View;

import Controller.Sommet;
import Model.ModeleTable;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
/**
 *
 * @author User
 */
public class SwingContainer {
    private JFrame mainFrame;
    public static java.util.List<Sommet> listeSommets;
    public static java.util.List<Edge> listeArretes;    
    private Graph g;
    
    private MenuBar menuBar;
    private EditPanel editPanel;
    private SplitPanel splitPanel;
    private InfoPanel infoPanel;
    private GraphPanel graphPanel;
    public static SwingContainer myWindow;
    
    public SwingContainer(){}
    
    public static void main(String[] args){
        try{
            myWindow = new SwingContainer();
            myWindow.prepareGUI();
        }catch(Exception E){
            E.printStackTrace();
        }        
    }

    private void prepareGUI() throws IOException{
        // Setting up the main frame
        mainFrame = new JFrame("Logiciel de gestion et d’analyse de graphes dynamiques");
        mainFrame.setSize(1800, 1000);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        

        // Create Graph (left) and Edit (right) panel
        graphPanel = new GraphPanel();
        editPanel = new EditPanel();
        
        // Add Menu Bar
        menuBar = new MenuBar();
        mainFrame.setJMenuBar(menuBar.getJMenuBar());
        
        // Add SplitPanel which contains GraphPanel and EditPanel
        splitPanel = new SplitPanel(JSplitPane.HORIZONTAL_SPLIT, graphPanel, editPanel);
        mainFrame.add(splitPanel, BorderLayout.CENTER);
        
        // Add infoPanel (contains information about the Graph)
        infoPanel = new InfoPanel();        
        mainFrame.add(infoPanel, BorderLayout.PAGE_END);
        
        mainFrame.setVisible(true);
    }

    public void updateTable() {
        ModeleTable modele = new ModeleTable();
        int sumDegrees = 0;
        int order = 0;
        boolean connexity = false;

        modele.supprimeToutesLesLigne();
        System.out.println("test");
        for (int i = 0; i < listeSommets.size(); i++) {
            sumDegrees += listeSommets.get(i).getDegre();
            order++;
            Object[] row = {listeSommets.get(i).getNom(), listeSommets.get(i).toStringSommetsAdjacents(), listeSommets.get(i).getDegre()};
            modele.addRow(row);
        }

        ConnectedComponents cc = new ConnectedComponents();
        cc.init(g);

        InfoGraphPanel infoGraphPanel = infoPanel.getInfoGraphPanel();

        if (cc.getConnectedComponentsCount() == 1) {
            infoGraphPanel.setTextFieldConnexe("Oui");
        } else {
            infoGraphPanel.setTextFieldConnexe("Non");
        }
        
        infoGraphPanel.setTextFieldOrdre(order);
        infoGraphPanel.setTextFieldTotDegre(sumDegrees);
        infoGraphPanel.setTextFieldTaille(sumDegrees/2);
    }

    public GraphPanel getGraphPanel() {
        return graphPanel;
    }
    
}