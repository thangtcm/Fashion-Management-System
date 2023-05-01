/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.net.URL;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author couni
 */
public class PathToIconConverter {
    public static ArrayList<Icon> convertToIcons(ArrayList<String> imagePaths) {
        ArrayList<Icon> icons = new ArrayList<>();
        for (String imagePath : imagePaths) {
            URL imageURL = PathToIconConverter.class.getResource(imagePath);
            ImageIcon imageIcon = new ImageIcon(imageURL);
            icons.add(imageIcon);
        }
        return icons;
    }
}
