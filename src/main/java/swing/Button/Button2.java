/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing.Button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author YAN
 */
public final class Button2 extends JButton {

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Button2() {
        //  Init Color
        this.sizeBorder = 2;
        borderColor = new Color(79,98,203);
        backgroundColor = Color.WHITE;
        colorHover = new Color(79,98,203);
        BackgroundHoverColor = new Color(79,98,203);
        colorForeground = Color.WHITE;
        setBackground(BackgroundHoverColor);
        setForeground(colorForeground);
        setContentAreaFilled(false);
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(backgroundColor);
                setForeground(colorHover);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(BackgroundHoverColor);
                setForeground(colorForeground);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(BackgroundHoverColor);
                setForeground(colorForeground);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(getBackgroundColor());
                    setForeground(colorHover);
                } else {
                    setBackground(getBackgroundColor());
                    setForeground(colorForeground);
                }
            }
        });
    }

    private boolean over;
    private Color backgroundColor;
    private Color colorHover;
    private Color BackgroundHoverColor;
    private Color colorForeground;
    private Color borderColor;
    private int radius = 0;
    private int sizeBorder;

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(this.sizeBorder, this.sizeBorder, getWidth() - this.sizeBorder*2, getHeight() - this.sizeBorder*2, radius, radius);
        super.paintComponent(grphcs);
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

    /**
     * @return the colorHover
     */
    public Color getColorHover() {
        return colorHover;
    }

    /**
     * @param colorHover the colorHover to set
     */
    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    /**
     * @return the BackgroundHoverColor
     */
    public Color getBackgroundHoverColor() {
        return BackgroundHoverColor;
    }

    /**
     * @param BackgroundHoverColor the BackgroundHoverColor to set
     */
    public void setBackgroundHoverColor(Color BackgroundHoverColor) {
        this.BackgroundHoverColor = BackgroundHoverColor;
    }

    /**
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @param backgroundColor the backgroundColor to set
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * @return the colorForeground
     */
    public Color getColorForeground() {
        return colorForeground;
    }

    /**
     * @param colorForeground the colorForeground to set
     */
    public void setColorForeground(Color colorForeground) {
        this.colorForeground = colorForeground;
    }
}