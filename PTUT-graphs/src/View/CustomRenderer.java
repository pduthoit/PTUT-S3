/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static View.SwingContainer.connexity;
import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 *
 * @author User
 */
public class CustomRenderer extends BarRenderer{

   public CustomRenderer()
   {
   }

   public Paint getItemPaint(final int row, final int column)
   {
       return (connexity) ? new Color(61,210,61) : new Color(210,64,61);
   }
}