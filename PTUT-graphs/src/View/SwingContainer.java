package View;

import Controller.Sommet;
import Model.ModeleTable;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
/**
 *
 * @author User
 */
public class SwingContainer {
    private JFrame mainFrame;
    public static java.util.List<Sommet> listeSommets;
    public static java.util.List<Edge> listeArretes;    
    public static SwingContainer myWindow;
    public static Graph g;
    public static ModeleTable modele;
    
    private MenuBar menuBar;
    private EditPanel editPanel;
    private SplitPanel splitPanel;
    private InfoPanel infoPanel;
    private GraphPanel graphPanel;
    
    
    public SwingContainer(){}
    
    public static void main(String[] args){
        try{
            modele = new ModeleTable();
            g = new SingleGraph("g");
            listeSommets = new ArrayList<Sommet>();
            listeArretes = new ArrayList<Edge>();
            myWindow = new SwingContainer();
            myWindow.prepareGUI();
            myWindow.updateTable();
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
        for(int i = 0; i < g.getNodeCount(); i++){  // Récupère tous les sommets du Graphe pour les mettre dans listeSommets
            listeSommets.add(new Sommet(g.getNode(i).getIndex() , g.getNode(i).getDegree()));
        }
        int sumDegrees = 0;
        int order = 0;

        modele.supprimeToutesLesLigne();
        
        System.out.println("updateTable()\n");
        //Pour chaque noeuds de la listeSommets
        for (int i = 0; i < listeSommets.size(); i++) {
            sumDegrees += listeSommets.get(i).getDegre(); // ajoute degré noeud courant à la somme des degrés
            order++; // incrémente l'ordre du graphe
            // créer une ligne a ajouter à notre modèle de table qui contient : le nom du sommet, le nom de ses sommets adjacents et le degré du sommet
            Object[] row = {listeSommets.get(i).getId(), listeSommets.get(i).toStringSommetsAdjacents(), listeSommets.get(i).getDegre()}; 
            modele.addRow(row); // ajoute la ligne au modèle
        }

        ConnectedComponents cc = new ConnectedComponents();
        cc.init(g);

        InfoGraphPanel infoGraphPanel = this.getInfoPanel().getInfoGraphPanel();
        System.out.println(infoGraphPanel);
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
    
    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
    
}
