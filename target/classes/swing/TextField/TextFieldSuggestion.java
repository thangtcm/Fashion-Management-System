/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing.Textfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */

public class TextFieldSuggestion extends JTextField {

    private final TextFieldSuggestionUI textUI;
    private String labelText = "Label";
    private boolean isUserInput = false;
    public TextFieldSuggestion() {
        //setOpaque(true);
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
        if(getText().equals(""))
        {
            setText(labelText);
            setForeground(Color.GRAY);
        }
        textUI = new TextFieldSuggestionUI(this);
        setUI(textUI);
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

    public void addItemSuggestion(String text) {
        textUI.getItems().add(text);
    }

    public void removeItemSuggestion(String text) {
        textUI.getItems().remove(text);
    }

    public void clearItemSuggestion() {
        textUI.getItems().clear();
    }

    public void setRound(int round) {
        textUI.setRound(round);
    }

    public int getRound() {
        return textUI.getRound();
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
}
