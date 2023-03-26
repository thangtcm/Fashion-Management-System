/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author couni
 */
public class Categories {

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
     * @return the CategoriesName
     */
    public String getCategoriesName() {
        return CategoriesName;
    }

    /**
     * @param CategoriesName the CategoriesName to set
     */
    public void setCategoriesName(String CategoriesName) {
        this.CategoriesName = CategoriesName;
    }

    /**
     * @return the Decription
     */
    public String getDecription() {
        return Decription;
    }

    /**
     * @param Decription the Decription to set
     */
    public void setDecription(String Decription) {
        this.Decription = Decription;
    }
    private int ID;
    private String CategoriesName;
    private String Decription;
}
