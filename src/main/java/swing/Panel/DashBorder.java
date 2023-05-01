/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JLabel;

/**
 *
 * @author couni
 */
public class DashBorder extends JLabel{
    
    private int sizeBorder = 5;
    private Color ColorBorderDash = Color.BLACK;
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(sizeBorder, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
        g2d.setStroke(dashed);
        g2d.setColor(ColorBorderDash);
        g2d.drawRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }


    /**
     * @return the ColorBorderDash
     */
    public Color getColorBorderDash() {
        return ColorBorderDash;
    }

    /**
     * @param ColorBorderDash the ColorBorderDash to set
     */
    public void setColorBorderDash(Color ColorBorderDash) {
        this.ColorBorderDash = ColorBorderDash;
    }

    /**
     * @return the sizeBorder
     */
    public int getSizeBorder() {
        return sizeBorder;
    }

    /**
     * @param sizeBorder the sizeBorder to set
     */
    public void setSizeBorder(int sizeBorder) {
        this.sizeBorder = sizeBorder;
    }
}
