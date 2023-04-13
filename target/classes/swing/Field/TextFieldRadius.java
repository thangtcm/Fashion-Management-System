/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Field;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import shadow.ShadowRenderer;

/**
 *
 * @author couni
 */
public class TextFieldRadius extends JTextField{
    
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }
    
    private String labelText = "Label";
    private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);
    private BufferedImage imageShadow;
    private Insets shadowSize = new Insets(2, 5, 8, 5);
    private boolean isUserInput = false;

    public TextFieldRadius() {
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                if(getText().equals(labelText))
                {
                    setText("");
                    setForeground(Color.BLACK);
                    isUserInput = true;
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if(getText().equals(""))
                {
                    setText(labelText);
                    setForeground(Color.GRAY);
                    isUserInput = false;
                }
            }
        });
        
        setUI(new TextUI());
        setOpaque(false);
        setForeground(new Color(80, 80, 80));
        setSelectedTextColor(new Color(255, 255, 255));
        setSelectionColor(new Color(133, 209, 255));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
        if(getText().equals(""))
        {
            setForeground(Color.GRAY);
            setText(labelText);
        }
    }
    
    public String getTextInput()
    {
        if(getText().equals(labelText))
        {
            return "";
        }
        else
            return getText();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Gọi phương thức PlaceHolder() trong phương thức paint()
        if(!isUserInput)
        {
            if(getText().equals(""))
            {
                setText(labelText);
                setForeground(Color.GRAY);
            }
        }
        
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (getShadowSize().left + getShadowSize().right);
        double height = getHeight() - (getShadowSize().top + getShadowSize().bottom);
        double x = getShadowSize().left;
        double y = getShadowSize().top;
        //  Create Shadow Image
        g2.drawImage(imageShadow, 0, 0, null);
        //  Create Background Color
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        g2.dispose();
        
        super.paintComponent(grphcs);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (getShadowSize().left + getShadowSize().right);
        int height = getHeight() - (getShadowSize().top + getShadowSize().bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
        } else {
            return null;
        }
    }

    private class TextUI extends BasicTextFieldUI {

        //  Override this method to remove background or not paint background
        @Override
        protected void paintBackground(Graphics grphcs) {

        }
    }

    /**
     * @return the labelText
     */
    public String getLabelText() {
        return labelText;
    }

    /**
     * @param labelText the labelText to set
     */
    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    /**
     * @return the shadowSize
     */
    public Insets getShadowSize() {
        return shadowSize;
    }

    /**
     * @param shadowSize the shadowSize to set
     */
    public void setShadowSize(Insets shadowSize) {
        this.shadowSize = shadowSize;
    }
}
