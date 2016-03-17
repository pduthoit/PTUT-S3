package View;

import Controller.Sommet;
import static View.SwingContainer.g;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
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
        g.addAttribute("ui.quality");
        g.addAttribute("ui.antialias");
        g.addAttribute("layout.stabilization-limit:0.95");
        g.addAttribute("ui.stylesheet","node { shape: circle;"
                + "fill-color: #223; "
                + "shape: rounded-box;  "
                + "stroke-mode: plain;"
                + "stroke-color: #333;"
                + "size:3px;"
                + "text-visibility-mode:hidden;"
                + "}"
                + "edge { fill-color: #333;"
                + "text-visibility-mode:hidden;"
                + "shape: line;"
                + "arrow-size: 3px, 2px;}");
        
        
        vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        vue.enableAutoLayout(); 
        view.getCamera().resetView();
        view = vue.addDefaultView(false);
        
    } 
}
