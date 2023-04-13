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
     * @return the 
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the  to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public Categories(String Name)
    {
        this.CategoriesName = Name;
    }
    
    public Categories()
    {
        
    }
    
    @Override
    public String toString()
    {
        return this.CategoriesName;
    }
    private int ID;
    private String CategoriesName;
    private String Description;
}
