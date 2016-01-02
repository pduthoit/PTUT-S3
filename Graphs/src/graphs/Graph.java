
package graphs;

import java.util.List;
import org.graphstream.graph.implementations.*;

public class Graph extends SingleGraph {
    
    private static int nbrNoeud;
    private static int nbrLien;
    private static List<String> listNoeud;
    private static List<String> listLien;
    
    public Graph(String id,int nbrNoeud, int nbrLien) {
        super(id);
        this.listNoeud = ajouterNNoeuds(this,nbrNoeud,listNoeud);
        this.listLien = ajouterNliens(this,nbrLien,listLien);
    }
    
    public static List<String> ajouterNNoeuds(Graph graph,int nbNoeuds,List<String> listNoeud){
        for(int i=0;i<nbNoeuds;i++){
            graph.addNode("A"+i);
            listNoeud.add("A"+i);
        }
        return listNoeud;
    }
        
    public static List<String> ajouterNliens(Graph graph, int nbLiens, List<String> listLien){
        for(int i =0;i<nbLiens;i++){
            int Noeud1 = (int) ((Math.random())%nbrNoeud);
            int Noeud2 = (int) ((Math.random())%nbrNoeud);
            graph.addEdge("0"+i, Noeud1, Noeud2);
            listLien.add("0"+i);
        }
        return listLien;
    }
    
}
