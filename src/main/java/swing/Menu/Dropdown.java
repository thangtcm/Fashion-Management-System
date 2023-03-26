/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Menu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author couni
 */
public class Dropdown extends JFrame{

    private JButton ellipsisButton;
    private JPopupMenu popupMenu;
    
    public Dropdown() {
        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());
        
        // Create the ellipsis icon
        ImageIcon icon = new ImageIcon("ellipsis.png");
        
        // Create the ellipsis button and add the icon
        ellipsisButton = new JButton(icon);
        ellipsisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(ellipsisButton, 0, ellipsisButton.getHeight());
            }
        });
        
        // Create the popup menu and add the menu items
        popupMenu = new JPopupMenu();
        JMenuItem item1 = new JMenuItem("Action 1");
        JMenuItem item2 = new JMenuItem("Action 2");
        JMenuItem item3 = new JMenuItem("Action 3");
        popupMenu.add(item1);
        popupMenu.add(item2);
        popupMenu.add(item3);
        
        // Add the ellipsis button to the frame
        add(ellipsisButton);
        setVisible(true);
    }
}