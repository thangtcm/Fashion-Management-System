/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Badge;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

/**
 *
 * @author couni
 */
public final class Badge extends JLabel{
    private Color borderColor;
    private int radius = 0;

    /**
     * @return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public Badge()
    {
        //setSize(110, 35);
        setPreferredSize(new Dimension(110,35));
        radius = 35;
        setBackground(new Color(255,224,187));
        borderColor = new Color(240,150,100);
        setForeground(new Color (245,118,47));
        setFont(new Font("Inter", Font.PLAIN, 12));
        setHorizontalAlignment(CENTER);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(getBorderColor());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getRadius(), getRadius());
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, getRadius(), getRadius());
        super.paintComponent(grphcs);
    }
}
