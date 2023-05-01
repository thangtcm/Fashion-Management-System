/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Button;

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
 * @author couni
 */
public final class ButtonEdit extends JButton {

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

    public ButtonEdit() {
        //  Init Color
        this.sizeBorder = 2;
        BackgroundColor = new Color(179, 250, 160);
        borderColor = new Color(30, 136, 56);
        BackgroundHover = Color.WHITE;
        Color = Color.WHITE;
        ColorHover = new Color(179, 250, 160);
        setBackground(BackgroundColor);
        setForeground(Color);
        setContentAreaFilled(false);
        //  Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(BackgroundHover);
                setForeground(ColorHover);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(BackgroundColor);
                setForeground(Color);
                over = false;

            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(BackgroundColor);
                setForeground(Color);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(BackgroundHover);
                    setForeground(ColorHover);
                } else {
                    setBackground(BackgroundColor);
                    setForeground(Color);
                }
            }
        });
    }

    
    
    
    private boolean over;
    private Color BackgroundHover;
    private Color ColorHover;
    private Color Color;
    private Color BackgroundColor;
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
     * @return the BackgroundHover
     */
    public Color getBackgroundHover() {
        return BackgroundHover;
    }

    /**
     * @param BackgroundHover the BackgroundHover to set
     */
    public void setBackgroundHover(Color BackgroundHover) {
        this.BackgroundHover = BackgroundHover;
    }

    /**
     * @return the ColorHover
     */
    public Color getColorHover() {
        return ColorHover;
    }

    /**
     * @param ColorHover the ColorHover to set
     */
    public void setColorHover(Color ColorHover) {
        this.ColorHover = ColorHover;
    }

    /**
     * @return the Color
     */
    public Color getColor() {
        return Color;
    }

    /**
     * @param Color the Color to set
     */
    public void setColor(Color Color) {
        this.Color = Color;
    }

    /**
     * @return the Background
     */
    public Color getBackgroundColor() {
        return BackgroundColor;
    }

    /**
     * @param Background the Background to set
     */
    public void setBackgroundColor(Color Background) {
        this.BackgroundColor = Background;
    }
}
