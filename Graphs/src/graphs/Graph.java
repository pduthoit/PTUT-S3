
package graphs;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Graph extends SingleGraph {
    
    private static int nbrNoeud;
    private static int nbrLien;
    
    public Graph(String id,int nbrNoeud, int nbrLien) {
        super(id);
        this.nbrNoeud = ajouterNNoeuds(this,nbrNoeud);
        this.nbrLien = nbrLien;
    }
    
    public static int ajouterNNoeuds(Graph graph,int nbNoeuds){
        for(int i=0;i<nbNoeuds;i++){
            graph.addNode("A"+i);
        }
        return nbNoeuds;
    }
        
    public static int ajouterNliens(Graph graph, int nbLiens){
        for(int i = 0;i<nbLiens;i++){
            int Noeud1 = Math.random % nbrNoeud;
            graph.addEdge(null, null, null);
        }
        return nbLiens;
    }
    
}
