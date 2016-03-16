package Controller;

import java.util.HashSet;
import java.util.Iterator;

public class Sommet {
 
    private String nom;
    private HashSet <String> sommetsAdjacents;
    private int degre;

    public void setDegre(int degre) {
        this.degre = degre;
    }
    
    public Sommet(String nom, int degre) {
        this.nom = nom;
        this.degre = degre;
        sommetsAdjacents = new HashSet <String>();
    }
    public String getNom() {
        return nom;
    }

    public HashSet<String> getSommetsAdjacents() {
        return sommetsAdjacents;
    }
    
    public String toStringSommetsAdjacents() {
        String liste = "";
        Iterator<String> it = sommetsAdjacents.iterator();
        while (it.hasNext()) {
            liste = liste + it.next() + " ";
        }
        
        return liste;
    }
    
    public void addSommetsAdjacents(String s) {
        sommetsAdjacents.add(s);
    }

    public int getDegre() {
        return degre;
    }
}
