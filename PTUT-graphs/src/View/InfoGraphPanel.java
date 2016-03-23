package View;

import static View.SwingContainer.myWindow;
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
    final private JLabel labelComposanteConnexe;
    final private JLabel labelTotDegre;
    final private JLabel labelTaille;
    final private JLabel labelOrdre;
    final private JTextField fieldConnexe;
    final private JTextField fieldComposanteConnexe;
    final private JTextField fieldTotDegre;
    final private JTextField fieldTaille;
    final private JTextField fieldOrdre;
    
    public InfoGraphPanel(){

        this.setLayout(new FlowLayout());
        
        labelConnexe = new JLabel("Connexité :");
        labelComposanteConnexe = new JLabel("Composante(s) connexe(s) :");
        labelTotDegre = new JLabel("Total des degrés :");
        labelTaille = new JLabel("Taille :");
        labelOrdre = new JLabel("Ordre :");
        
        fieldConnexe = new JTextField();
        fieldComposanteConnexe = new JTextField();
        fieldTotDegre = new JTextField();
        fieldTaille = new JTextField();
        fieldOrdre = new JTextField();
        
        fieldConnexe.setPreferredSize(new Dimension(50, 24));
        fieldComposanteConnexe.setPreferredSize(new Dimension(50, 24));
        fieldTotDegre.setPreferredSize(new Dimension(50, 24));
        fieldTaille.setPreferredSize(new Dimension(50, 24));
        fieldOrdre.setPreferredSize(new Dimension(50, 24));
        
        fieldConnexe.setEditable(false);
        fieldComposanteConnexe.setEditable(false);
        fieldTotDegre.setEditable(false);
        fieldTaille.setEditable(false);
        fieldOrdre.setEditable(false);
        
        fieldConnexe.setHorizontalAlignment(JTextField.CENTER);
        fieldComposanteConnexe.setHorizontalAlignment(JTextField.CENTER);
        fieldTotDegre.setHorizontalAlignment(JTextField.CENTER);
        fieldTaille.setHorizontalAlignment(JTextField.CENTER);
        fieldOrdre.setHorizontalAlignment(JTextField.CENTER);
        
        
        this.add(labelConnexe);
        this.add(fieldConnexe);
        this.add(labelComposanteConnexe);
        this.add(fieldComposanteConnexe);
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
    
    public void setTextFieldCompConnexe(int totCompConn) {
        this.fieldComposanteConnexe.setText(Integer.toString(totCompConn));
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
    
    public InfoGraphPanel getInfoGraphPanel(){
        System.out.println("getInfoGraphPanel()");
        return this;
    }
        
}
