/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.SwingContainer.g;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.graphstream.algorithm.ConnectedComponents;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author User
 */
public class BarChart{
    private ChartPanel barPanel;
    public DefaultCategoryDataset barChartData;
    private CategoryPlot categoryPlot;
    private BarRenderer br;
    private static int id;
    private JFreeChart barChart;
    public BarChart(){
        barChartData = new DefaultCategoryDataset();
        id = 0;
        barChartData.setValue(g.getNodeCount(), "Nombre de noeuds", "T"+id);
        
        
        barChart = ChartFactory.createBarChart("", "Etape : T","Nombre de noeuds", barChartData, PlotOrientation.VERTICAL, false, true, false);
                
        barChart.setBackgroundPaint(new Color(240,240,240));
        
        categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        categoryPlot.setOutlineVisible(false);
        categoryPlot.setBackgroundPaint(new Color(250,250,250));
        categoryPlot.setRangeGridlinePaint(new Color(180,180,180));
                
        br = (BarRenderer) categoryPlot.getRenderer();
        br.setMaximumBarWidth(.02);
        br.setBarPainter(new StandardBarPainter());
        br.setItemMargin(0.0);
        
        CategoryAxis rangeAxis = categoryPlot.getDomainAxis();
         
        rangeAxis.setLowerMargin(0.0);
        rangeAxis.setUpperMargin(0.0);
        rangeAxis.setCategoryMargin(0.01);

        
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
  
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
      
        
//        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
//        renderer.setDrawBarOutline(false);
        
//        categoryPlot.setRangeGridlinePaint(Color.WHITE);
        categoryPlot.getRendererForDataset(categoryPlot.getDataset(0)).setSeriesPaint(0, new Color(61,210,61));
        barPanel = new ChartPanel(barChart); 
        barPanel.setVisible(true);
//        barPanel.setSize(400, 400);
    }
    
    public ChartPanel getChart(){
        return barPanel;
    }
    
    public void setValueData(int i){
        id++;
        System.out.println("ca marche "+i+id);
        barChartData.setValue(i, "Nombre de noeuds", "T"+id);
        ((BarRenderer)categoryPlot.getRenderer()).setBarPainter(new StandardBarPainter());
        ConnectedComponents cc = new ConnectedComponents();
        cc.init(g);
        
        
        
        if(cc.getConnectedComponentsCount()==1){
            categoryPlot.getRendererForDataset(categoryPlot.getDataset()).setSeriesPaint(0, new Color(61,210,61));
            System.out.println("id : "+id+"\ndataset : "+categoryPlot.getDataset(id));
        }
        else {
            System.out.println("id : "+id+"\ndataset : "+categoryPlot.getDataset(id));
            categoryPlot.getRendererForDataset(categoryPlot.getDataset()).setSeriesPaint(0, new Color(210,64,61));
        }
        
    }
}
