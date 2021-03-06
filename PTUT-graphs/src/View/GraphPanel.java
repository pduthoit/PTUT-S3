package View;

import Controller.Sommet;
import static View.SwingContainer.g;
import static View.SwingContainer.listeArretes;
import static View.SwingContainer.listeSommets;
import static View.SwingContainer.myWindow;
import static View.SwingContainer.nbConnComp;
import static View.SwingContainer.dynFuncIsRunning;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import org.graphstream.graph.Edge;

import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceFactory;
import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

/**
 *
 * @author User
 */
public class GraphPanel extends JPanel {

    public static Viewer vue;
    public static View view;
    private Random randomGenerator = new Random();

    public GraphPanel() throws IOException {
        this.setLayout(new BorderLayout());
        g.addAttribute("ui.quality");
        g.addAttribute("ui.antialias");
        g.addAttribute("ui.stylesheet", "url('file:./src/stylesheet')");

        g.setStrict(false);
        g.setAutoCreate(true);
        g.addEdge("AB", "A", "B");
        g.addEdge("BC", "B", "C");
        g.addEdge("CA", "C", "A");
        g.addEdge("AD", "A", "D");
        g.addEdge("DE", "D", "E");
        g.addEdge("DF", "D", "F");
        g.addEdge("EF", "E", "F");

        vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);

        vue.enableAutoLayout();

        view = vue.addDefaultView(false);

        // Event qui récupère l'id du noeud clické
        view.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                click(e);
            }
        });

        this.removeAll();
        this.add((Component) view, BorderLayout.CENTER);
    }

    public void click(MouseEvent e) {
        int buttonDown = e.getButton();

        GraphicElement n = view.findNodeOrSpriteAt(e.getX(), e.getY());
        System.out.println(n + "\n");
        if (n != null) {
            if (buttonDown == MouseEvent.BUTTON1) { // si clic-gauche sur un noeud
                System.out.println("L'id du noeud est : " + n.getId());
            } else if (buttonDown == MouseEvent.BUTTON3) {  // si clic-droit sur un noeud
                PopUpMenu p = new PopUpMenu(n, true);
                p.showPopup(e);
            }
        } else if (n == null) {
            if (buttonDown == MouseEvent.BUTTON3) {  // si clic-droit dans le vide
                PopUpMenu p = new PopUpMenu(n, false);
                p.showPopup(e);
            }
        }
    }
    
    public void updateAllInfo(){
        myWindow.updateTable();
        myWindow.getBarChart().setValueData(g.getNodeCount()); // édite le graphique
        myWindow.getCCChart().setValueData(nbConnComp);
    }
    
    public void removeGraphNode(String id) {
        Iterator<Sommet> it = listeSommets.iterator();
        while (it.hasNext()) {
            Sommet node = it.next();
            if (node.getId().equals(id)) {
                it.remove();
            }
        }

        g.removeNode(id);
        System.out.println("Le noeud " + id + " a été supprimé.\n");
        EditPanel ep = myWindow.getEditPanel();
        ep.setCodeArea("g.removeNode(\"" + id + "\");\n");

        updateAllInfo();
    }
    
    public void deleteRandomNode() {
        dynFuncIsRunning = true;
        myWindow.getMenuBar().setMenu2Item2Clickable(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                if(g.getNodeCount() != 0 && dynFuncIsRunning) {
                    randomGenerator = new Random();
                    int index = randomGenerator.nextInt(listeSommets.size());
                    Sommet node = listeSommets.get(index);
                    String nomNode = node.getId();
                    System.out.println("L'id du sommet random est : "+nomNode);
                    Iterator<Sommet> it = listeSommets.iterator();
                    while (it.hasNext()){
                    node = it.next();
                        if (node.getId().equals(nomNode)) {
                            it.remove();
                        }
                    }
                    g.removeNode(nomNode);
                    System.out.println("Le noeud " + nomNode + " a été supprimé.\n");
                    System.out.println("Le nb de noeud " + g.getNodeCount()+ "\n");
                    EditPanel ep = myWindow.getEditPanel();
                    ep.setCodeArea("g.removeNode(\"" + nomNode + "\");\n");
                    updateAllInfo();
                } else {
                   timer.cancel();
                   timer.purge();
                   dynFuncIsRunning = false;
                   myWindow.getMenuBar().setMenu2Item2Clickable(false);
                }        
            }
        };
        timer.schedule(task, 0L ,2000L);
    }
    
    public void deleteRandomEdge() {
        dynFuncIsRunning = true;
        myWindow.getMenuBar().setMenu2Item2Clickable(true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                if(g.getEdgeCount() != 0 && dynFuncIsRunning) {
                    randomGenerator = new Random();
                    int index = randomGenerator.nextInt(listeArretes.size());
                    Edge edge = listeArretes.get(index);
                    String nomEdge = edge.getId();
                    Iterator<Edge> it = listeArretes.iterator();
                    while (it.hasNext()) {
                        edge = it.next();
                        if (edge.getId().equals(nomEdge)) {
                            it.remove();
                        }
                    }
                    g.removeEdge(nomEdge);
                    System.out.println("Le noeud " + nomEdge + " a été supprimé.\n");
                    System.out.println("Le nb de noeud " + g.getEdgeCount()+ "\n");
                    EditPanel ep = myWindow.getEditPanel();
                    ep.setCodeArea("g.removeEdge(\"" + nomEdge + "\");\n");

                    updateAllInfo();
                }else {
                   timer.cancel();
                   timer.purge();
                   dynFuncIsRunning = false;
                   myWindow.getMenuBar().setMenu2Item2Clickable(false);
                }        
            }
        };
        timer.schedule(task, 0L ,2000L);
        
    }

    public void createGraphNode() {
        JPanel myPanel = new JPanel();
        JTextField sommet = new JTextField(5);
        boolean test = false;

        myPanel.add(new JLabel("Nom du sommet :"));
        myPanel.add(sommet);
        int result;
        do {
            result = JOptionPane.showConfirmDialog(null, myPanel, "Saisir le nom du sommet à ajouter", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                break;
            }
            for (int i = 0; i < listeSommets.size(); i++) {
                if (listeSommets.get(i).getId().equals(sommet.getText())) {
                    test = true;
                    System.out.println("Erreur : Il existe déjà un noeud portant ce nom \n");
                } else {
                    test = false;
                }
            }

        } while (!test && result != JOptionPane.OK_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String s = sommet.getText();
            g.addNode(s);
            g.getNode(s).setAttribute("ui.label", s);

            listeSommets.add(new Sommet(s, g.getNode(s).getDegree()));
            System.out.println("Un noeud a été créé.\n");
            myWindow.getEditPanel().setCodeArea("g.addNode(\"" + s + "\");\n");
            
            updateAllInfo();
        }
    }

    public void createGraphEdge() {
        JPanel myPanel = new JPanel();
        String[] listeNomsSommets = new String[listeSommets.size()];

        for (int i = 0; i < listeSommets.size(); i++) {
            listeNomsSommets[i] = listeSommets.get(i).getId();
        }
        // DefaultListModel dlm = new DefaultListModel();

        JList sommet1 = new JList(listeNomsSommets);
        JList sommet2 = new JList(listeNomsSommets);

        myPanel.add(new JLabel("Premier sommet :"));
        myPanel.add(new JScrollPane(sommet1));
        myPanel.add(new JLabel("Deuxième sommet :"));
        myPanel.add(new JScrollPane(sommet2));

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Saisir le nom des deux sommets", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String s1 = (String) sommet1.getSelectedValue();
            String s2 = (String) sommet2.getSelectedValue();
            g.addEdge(s1.concat(s2), s1, s2);
            vue.enableAutoLayout();

            listeArretes.add(g.getEdge(s1.concat(s2)));

            for (int i = 0; i < listeSommets.size(); i++) {
                if (listeSommets.get(i).getId().equals(s1)) {
                    listeSommets.get(i).setDegre(listeSommets.get(i).getDegre() + 1);
                    listeSommets.get(i).addSommetsAdjacents(s2);
                }
            }

            for (int i = 0; i < listeSommets.size(); i++) {
                if (listeSommets.get(i).getId().equals(s2)) {
                    listeSommets.get(i).setDegre(listeSommets.get(i).getDegre() + 1);
                    listeSommets.get(i).addSommetsAdjacents(s1);
                }
            }
            
            System.out.println("Une arrête a été créée.\n");
            myWindow.getEditPanel().setCodeArea("g.addEdge(\"" + s1.concat(s2) + "\", \"" + s1 + "\", \"" + s2 + "\");\n");
            
            updateAllInfo();
        }
    }

    public void setGraphFile(String path) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fs.end();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fs.removeSink(g);
        }
        System.out.println(g.getEdgeCount());

        g.addAttribute("ui.quality"); // augmente la qualité
        g.addAttribute("ui.antialias"); // anti-aliasing
        g.addAttribute("ui.stylesheet", "url('file:./src/stylesheet')"); // assigne une stylesheet

        // Varie la taille des noeuds en fonction de la taille du graphe
        if (g.getNodeCount() < 10) {
            g.addAttribute("ui.stylesheet", "node { size : 25px; }");
        } else if (g.getNodeCount() >= 10 && g.getNodeCount() < 50) {
            g.addAttribute("ui.styleshee", "node { size : 15px; }");
        } else if (g.getNodeCount() >= 50 && g.getNodeCount() < 150) {
            g.addAttribute("ui.stylesheet", "node { size : 5px; }");
        } else if (g.getNodeCount() >= 150) {
            g.addAttribute("ui.stylesheet", "node { size : 1px; }");
        }

        vue = new Viewer(g, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        vue.enableAutoLayout();
        myWindow.getEditPanel().resetCodeArea();
        SwingContainer.fillListeSommets();
        SwingContainer.fillListeArretes();
        
        myWindow.getBarChart().clearChart();
        myWindow.getCCChart().clearChart();
        updateAllInfo();
        view = vue.addDefaultView(false);
    }
}
