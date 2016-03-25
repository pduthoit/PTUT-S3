package View;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

public class EditPanel extends JPanel{
    
    public JTextArea codeArea;
    
    public EditPanel(){
        super();
        this.setLayout(new BorderLayout());

        codeArea = new JTextArea();
        codeArea.setColumns(20);
        codeArea.setFont(new java.awt.Font("Consolas", 0, 13));
        codeArea.setForeground(new Color(170,170,170));
        codeArea.setBackground(new Color(20,20,45));
        // Initialise le codeArea
        codeArea.append("g.addEdge(\"AB\", \"A\", \"B\");\n" +
                        "g.addEdge(\"BC\", \"B\", \"C\");\n" +
                        "g.addEdge(\"CA\", \"C\", \"A\");\n" +
                        "g.addEdge(\"AD\", \"A\", \"D\");\n" +
                        "g.addEdge(\"DE\", \"D\", \"E\");\n" +
                        "g.addEdge(\"DF\", \"D\", \"F\");\n" +
                        "g.addEdge(\"EF\", \"E\", \"F\");");
        
        DefaultCaret caret = (DefaultCaret)codeArea.getCaret(); 
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        codeArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        codeArea.setCaretColor(new Color(170,170,170));
        codeArea.setBorder( BorderFactory.createCompoundBorder(
                            codeArea.getBorder(), 
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        this.add(codeArea, BorderLayout.CENTER);
    }
    
    public JTextArea getCodeArea(){
        return codeArea;
    }
    public void setCodeArea(String str){
        codeArea.append(str);
    }
    public void resetCodeArea(){
        codeArea.setText("");
    }
}
