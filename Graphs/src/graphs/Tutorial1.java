package graphs;


import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
 
public class Tutorial1 {
        public static void main(String args[]) {
                Graph graph = new Graph("Tutorial 1");
 
                ajouterNNoeuds(graph,5);
                graph.display();
        }
}