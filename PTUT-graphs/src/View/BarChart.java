/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.SwingContainer.connexity;
import static View.SwingContainer.g;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author User
 */
public class BarChart {

    private static int id;

    public DefaultCategoryDataset barChartData;

    private ChartPanel barPanel;
    private CategoryPlot categoryPlot;
    private BarRenderer br;
    private JFreeChart barChart;

    public BarChart() {
        barChartData = new DefaultCategoryDataset();
        id = 0;
        barChartData.setValue(g.getNodeCount(), "Nombre de noeuds", "T" + id);

        barChart = ChartFactory.createBarChart("", "Etape : T", "Nombre de noeuds", barChartData, PlotOrientation.VERTICAL, false, true, false);

        barChart.setBackgroundPaint(new Color(240, 240, 240));

        categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        categoryPlot.setOutlineVisible(false);
        categoryPlot.setBackgroundPaint(new Color(250, 250, 250));
        categoryPlot.setRangeGridlinePaint(new Color(180, 180, 180));

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

        categoryPlot.getRendererForDataset(categoryPlot.getDataset(0)).setSeriesPaint(0, new Color(61, 210, 61));
        barPanel = new ChartPanel(barChart);
        barPanel.setVisible(true);
    }

    public ChartPanel getChart() {
        return barPanel;
    }

    public void clearChart() { // reset le graphique
        id = 0;
        barChartData.clear();
    }

    public void setValueData(int i) {
        id++;
        System.out.println("ca marche " + i + id);
        barChartData.setValue(i, "Nombre de noeuds", "T" + id);

        if (connexity) {
            categoryPlot.getRendererForDataset(categoryPlot.getDataset()).setSeriesPaint(0, new Color(61, 210, 61));
        } else {
            categoryPlot.getRendererForDataset(categoryPlot.getDataset()).setSeriesPaint(0, new Color(210, 64, 61));
        }

    }
}
