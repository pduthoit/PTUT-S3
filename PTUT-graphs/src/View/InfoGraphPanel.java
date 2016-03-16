package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author User
 */
public class InfoGraphPanel extends JPanel{
    
    final private JLabel labelConnexe;
    final private JLabel labelTotDegre;
    final private JLabel labelTaille;
    final private JLabel labelOrdre;
    final private JTextField fieldConnexe;
    final private JTextField fieldTotDegre;
    final private JTextField fieldTaille;
    final private JTextField fieldOrdre;
    
    public InfoGraphPanel(){

        this.setLayout(new FlowLayout());
        
        labelConnexe = new JLabel("Connexité :");
        labelTotDegre = new JLabel("Total des degrés :");
        labelTaille = new JLabel("Taille :");
        labelOrdre = new JLabel("Ordre :");
        
        fieldConnexe = new JTextField();
        fieldTotDegre = new JTextField();
        fieldTaille = new JTextField();
        fieldOrdre = new JTextField();
        
        fieldConnexe.setPreferredSize(new Dimension(30, 24));
        fieldTotDegre.setPreferredSize(new Dimension(30, 24));
        fieldTaille.setPreferredSize(new Dimension(30, 24));
        fieldOrdre.setPreferredSize(new Dimension(30, 24));
        
        this.add(labelConnexe);
        this.add(fieldConnexe);
        this.add(labelTotDegre);
        this.add(fieldTotDegre);
        this.add(labelTaille);
        this.add(fieldTaille);
        this.add(labelOrdre);
        this.add(fieldOrdre);
    }
    
    
    public void setTextFieldConnexe(String bool) {
        this.fieldConnexe.setText(bool);
    }

    public void setTextFieldTotDegre(int sumDegrees) {
        this.fieldTotDegre.setText(Integer.toString(sumDegrees));
    }
    
    public void setTextFieldTaille(int taille) {
        this.fieldTaille.setText(Integer.toString(taille));
    }
    
    public void setTextFieldOrdre(int ordre) {
        this.fieldOrdre.setText(Integer.toString(ordre));
    }
        
}
