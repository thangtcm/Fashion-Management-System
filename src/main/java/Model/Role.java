/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class Role {

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the RoleName
     */
    public String getRoleName() {
        return RoleName;
    }

    /**
     * @param RoleName the RoleName to set
     */
    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    private int ID;
    private String RoleName;
}
