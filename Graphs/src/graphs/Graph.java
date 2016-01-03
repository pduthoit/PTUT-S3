
package graphs;

import java.util.List;
import org.graphstream.graph.implementations.*;

public class Graph extends SingleGraph {
    
    private static int nbrNoeud;
    private static int nbrLien;
    private static List<String> listNoeud; //esssayer avec des hashmap
    private static List<String> listLien;
    
    public Graph(String id,int nbrNoeud, int nbrLien) {
        super(id);
        for(int i=0;i<nbrNoeud;i++){
            addNode("N"+i);
            //Graph.listNoeud.add("N"+i);
        }
        for(int i =0;i<=nbrLien;i++){
            String Noeud1 = "N" + ((int)(((Math.random())*10)%nbrNoeud));
            String Noeud2 = "N" + ((int)(((Math.random())*10)%nbrNoeud));
            addEdge("E"+i, Noeud1, Noeud2); /* ajouter un noeud doit se faire dans la classe principale, je dois trouver une solution.*/
            //listLien.add("E"+i);
        }
    }
}
