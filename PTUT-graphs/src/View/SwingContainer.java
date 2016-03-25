package View;

import Controller.Sommet;
import Model.ModeleTable;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import org.graphstream.algorithm.ConnectedComponents;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
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
    public static int sumDegrees = 0;
    public static int order = 0;
    public static boolean connexity = true;

    private MenuBar menuBar;
    private SplitPanel splitPanel;
    private EditPanel editPanel;
    private GraphPanel graphPanel;

    private JPanel southPanel;
    private InfoGraphPanel infoGraphPanel;
    private SplitPanelSouth splitPanelSouth;
    private InfoPanel infoPanel;
    private BarChart barChart;

    public SwingContainer() {
    }

    public static void main(String[] args) {
        try {
            // On instancie toutes nos variables statiques
            modele = new ModeleTable();
            g = new SingleGraph("g");
            listeSommets = new ArrayList<Sommet>();
            listeArretes = new ArrayList<Edge>();
            myWindow = new SwingContainer();
            myWindow.prepareGUI();
            SwingContainer.fillListeSommets();
            SwingContainer.fillListeArretes();
            myWindow.updateTable();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    private void prepareGUI() throws IOException {
        // Setting up the main frame
        mainFrame = new JFrame("Logiciel de gestion et d’analyse de graphes dynamiques");
        mainFrame.setSize(1800, 1000);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
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
        
        // Add SplitPanel which contains GraphPanel and EditPanel
        southPanel = new JPanel(new BorderLayout());
        
        // Create infoGraphPanel (contains info about g)
        infoGraphPanel = new InfoGraphPanel();
        
        // Create barChart and infoPanel (Table)
        barChart = new BarChart();
        infoPanel = new InfoPanel();
        
        // Add SplitPanel which contains the Chart and the Table
        splitPanelSouth = new SplitPanelSouth(JSplitPane.HORIZONTAL_SPLIT, barChart.getChart(), infoPanel);
        
        // Add the panels to southPanel
        southPanel.add(splitPanelSouth, BorderLayout.CENTER);
        southPanel.add(infoGraphPanel, BorderLayout.PAGE_START);
        
        // Add southPanel to the mainFrame
        mainFrame.add(southPanel, BorderLayout.PAGE_END);

        mainFrame.setVisible(true);
    }

    public static void fillListeSommets() { // set/reset la liste des sommets
        listeSommets.clear();
        for (int i = 0; i < g.getNodeCount(); i++) {  // Récupère tous les sommets du Graphe pour les mettre dans listeSommets
            listeSommets.add(new Sommet(g.getNode(i).getId(), g.getNode(i).getDegree()));
        }
    }
    
    public static void fillListeArretes() { // set/reset la liste des sommets
        listeArretes.clear();
        for (int i = 0; i < g.getEdgeCount(); i++) {  // Récupère tous les sommets du Graphe pour les mettre dans listeSommets
            listeArretes.add(g.getEdge(i));
        }
    }

    public void updateTable() {
        sumDegrees = 0;
        order = 0;

        modele.supprimeToutesLesLigne(); // reset the model

        //Pour chaque noeuds de la listeSommets
        for (int i = 0; i < listeSommets.size(); i++) {
            sumDegrees += listeSommets.get(i).getDegre(); // ajoute degré noeud courant à la somme des degrés
            order++; // incrémente l'ordre du graphe

            // créer une ligne a ajouter à notre modèle de table qui contient : le nom du sommet, le nom de ses sommets adjacents et le degré du sommet
            String sommetsAdjacents = "";

            Iterator itr = g.getNode(i).getNeighborNodeIterator(); // créé une concaténation des sommets adjacents au noeud 
            while (itr.hasNext()) {
                sommetsAdjacents = sommetsAdjacents + " " + itr.next();
            }

            Object[] row = {listeSommets.get(i).getId(), sommetsAdjacents, listeSommets.get(i).getDegre()};

            modele.addRow(row); // ajoute la ligne au modèle
        }

        // permet de vérifier la connexité
        ConnectedComponents cc = new ConnectedComponents();
        cc.init(g);
        if (cc.getConnectedComponentsCount() == 1) {
            connexity = true;
            infoGraphPanel.setTextFieldConnexe("Oui");
        } else {
            connexity = false;
            infoGraphPanel.setTextFieldConnexe("Non");
        }
        // remplit les JTextField
        infoGraphPanel.setTextFieldCompConnexe(cc.getConnectedComponentsCount());
        infoGraphPanel.setTextFieldOrdre(order);
        infoGraphPanel.setTextFieldTotDegre(sumDegrees);
        infoGraphPanel.setTextFieldTaille(sumDegrees / 2);
    }

    // getters et setters
    public GraphPanel getGraphPanel() {
        return graphPanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public InfoGraphPanel getInfoGraphPanel() {
        return infoGraphPanel;
    }

    public BarChart getBarChart() {
        return barChart;
    }

    public EditPanel getEditPanel() {
        return editPanel;
    }

}
