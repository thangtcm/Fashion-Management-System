/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.TableBuild;

import java.awt.Color;

/**
 *
 * @author couni
 */
public class ModelBadge {
    private String BadgeName;
    private Color TextColor;
    private Color Background;

    
    /**
     * @return the BadgeName
     */
    public String getBadgeName() {
        return BadgeName;
    }

    /**
     * @param BadgeName the BadgeName to set
     */
    public void setBadgeName(String BadgeName) {
        this.BadgeName = BadgeName;
    }

    /**
     * @return the TextColor
     */
    public Color getTextColor() {
        return TextColor;
    }

    /**
     * @param TextColor the TextColor to set
     */
    public void setTextColor(Color TextColor) {
        this.TextColor = TextColor;
    }

    /**
     * @return the Background
     */
    public Color getBackground() {
        return Background;
    }

    /**
     * @param Background the Background to set
     */
    public void setBackground(Color Background) {
        this.Background = Background;
    }
    
    public ModelBadge(String BadgeName, Color TextColor, Color Background)
    {
        this.BadgeName = BadgeName;
        this.TextColor = TextColor;
        this.Background = Background;
    }
    
}
