/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author couni
 */
public class EllipsisComboBox extends JComboBox<Object> {
    public EllipsisComboBox() {
        setBorder(BorderFactory.createEmptyBorder());
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton(new ImageIcon(getClass().getResource("/Images/Icons/ellipsis.png"))) {
                    @Override
                    public int getHeight() {
                        return EllipsisComboBox.this.getHeight();
                    }
                };
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setOpaque(false);
                button.setBackground(new Color(0, 0, 0, 0));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        showPopup();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        EllipsisComboBox.super.setPopupVisible(false);
                    }
                });
                return button;
            }
        });
    }
}
