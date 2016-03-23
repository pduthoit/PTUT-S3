package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class SplitPanelSouth extends JSplitPane {

    private boolean firstResize = true;

    public SplitPanelSouth(int split, JPanel graphPanel, JPanel editPanel) {
        super(split, graphPanel, editPanel);
        this.setContinuousLayout(true);
        this.setDividerLocation(700);
        this.setDividerSize(3);
        this.setUI(new BasicSplitPaneUI() {
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (firstResize) {
                    setDividerLocation(0.35);
                    firstResize = false;
                }
            }
        });
    }
}
