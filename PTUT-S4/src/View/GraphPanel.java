package View;

import Controller.Sommet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author User
 */
public class GraphPanel extends JPanel{
    
    private Graph g;
    private List<Sommet> listeSommets;

    private List<Edge> listeArretes;
    
    
    public GraphPanel(){
        this.setLayout(new BorderLayout());
        
        listeSommets = new ArrayList<Sommet>();
        listeArretes = new ArrayList<Edge>();
        
        g = new SingleGraph("graphe");
        g.setStrict(false);
        g.setAutoCreate(true);
        g.addEdge("AB", "A", "B" );
        g.addEdge("BC", "B", "C" );
        g.addEdge("CA", "C", "A" );
        g.addEdge("AD", "A", "D" );
        g.addEdge("DE", "D", "E" );
        g.addEdge("DF", "D", "F" );
        g.addEdge("EF", "E", "F" );
        
        g.addAttribute("ui.quality");
        g.addAttribute("ui.antialias");
        g.addAttribute("ui.stylesheet","node { shape: circle;"
                + "fill-color: #223; "
                + "shape: rounded-box;  "
                + "text-size: 20px;"
                + "text-alignment :center;"
                + "stroke-mode: plain;"
                + "stroke-color: #333;"
                + "size:25px;"
                + "text-mode:normal;"
                + "}"
                + "edge { fill-color: #333;"
                + "text-size: 20px;}");

        
        Viewer vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        vue.enableAutoLayout();
        
        View view = vue.addDefaultView(false);
        this.removeAll();
        this.add((Component) view, BorderLayout.CENTER);
    }
    
    public Graph getG() {
        return g;
    }

    public List<Sommet> getListeSommets() {
        return listeSommets;
    }

    public List<Edge> getListeArretes() {
        return listeArretes;
    }
    
}
