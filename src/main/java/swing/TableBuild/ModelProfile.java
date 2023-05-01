/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.TableBuild;

import javax.swing.Icon;

/**
 *
 * @author couni
 */
public class ModelProfile {
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ModelProfile(Icon icon, int Id) {
        this.icon = icon;
        this.Id = Id;
    }

    public ModelProfile() {
    }

    private Icon icon;
    private int Id;
}
