package View;

import static View.SwingContainer.g;
import static View.SwingContainer.myWindow;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author User
 */
public class GraphPanel extends JPanel{
    private Viewer vue;
    private View view;
    
    
    public GraphPanel() throws IOException{
        this.setLayout(new BorderLayout());
        g.addAttribute("ui.quality");
        g.addAttribute("ui.antialias");
        g.addAttribute("ui.stylesheet", "url('file:./src/stylesheet')");
            
        g.setStrict(false);
        g.setAutoCreate(true);
        g.addEdge("AB", "A", "B" );
        g.addEdge("BC", "B", "C" );
        g.addEdge("CA", "C", "A" );
        g.addEdge("AD", "A", "D" );
        g.addEdge("DE", "D", "E" );
        g.addEdge("DF", "D", "F" );
        g.addEdge("EF", "E", "F" );
        
        vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        vue.enableAutoLayout();
        
        view = vue.addDefaultView(false);
        this.removeAll();
        this.add((Component) view, BorderLayout.CENTER);
    }
    
    public void setGraphFile(String path){
        g.clear();
       
        FileSource fs = null;
        try {
            fs = FileSourceFactory.sourceFor(path);
        } catch (Exception ex) {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fs.addSink(g);

        try {
            fs.begin(path);

            while (fs.nextEvents()) {
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            fs.end();
        } catch(IOException e) {
            e.printStackTrace();
        } finally{
            fs.removeSink(g);
        }
        System.out.println(g.getEdgeCount());
        
        g.addAttribute("ui.quality"); // augmente la qualité
        g.addAttribute("ui.antialias"); // anti-aliasing
        g.addAttribute("ui.stylesheet", "url('file:./src/stylesheet')"); // assigne une stylesheet
        
        // Varie la taille des noeuds en fonction de la taille du graphe
        if(g.getNodeCount() < 10)
            g.addAttribute("ui.stylesheet", "node { size : 25px; }");
        else if(g.getNodeCount() >= 10 && g.getNodeCount() < 50)
            g.addAttribute("ui.stylesheet", "node { size : 15px; }");
        else if(g.getNodeCount() >= 50 && g.getNodeCount() < 150)
            g.addAttribute("ui.stylesheet", "node { size : 5px; }");
        else if(g.getNodeCount() >= 150)
            g.addAttribute("ui.stylesheet", "node { size : 1px; }");
        
        
        
        vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        vue.enableAutoLayout(); 
        myWindow.updateTable();
        view = vue.addDefaultView(false);
        
    } 
}
