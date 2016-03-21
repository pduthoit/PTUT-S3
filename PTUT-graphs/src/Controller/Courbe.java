package Controller;

import java.awt.Graphics;
import javax.swing.JFrame;
import static View.SwingContainer.g;
import java.awt.Color;
import java.util.ArrayList;

public class Courbe extends JFrame {

    private static int t;
    private ArrayList<Integer> listePoints;

    public Courbe() {
        setSize(800, 600);
        //t = 0;
        t = getSize().width/4;
    }

    public int f() {
        return (g.getNodeCount()) % getSize().height / 2;
    }

    @Override
    public void paint(Graphics gFen) {
        for (int x = 0; x <= t; x++) {
            gFen.setColor(Color.red);
            gFen.drawLine(x, getSize().height + g.getNodeCount(), x + 1, getSize().height+g.getNodeCount() + 1);
            gFen.setColor(Color.BLACK);
            gFen.drawLine(0,0,getSize().width,getSize().height);
            
        }
    }

    public void setVisible() {
        setVisible(true);
    }

    public ArrayList<Integer> getListePoints() {
        return listePoints;
    }

    public void setListePoints(ArrayList<Integer> listePoints) {
        this.listePoints = listePoints;
    }
}
