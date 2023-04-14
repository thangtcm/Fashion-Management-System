/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Swing.Table.EventAction;
import Swing.Table.ModelThreeAction;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author couni
 */
public class Service {
    private Integer ID;
    private String ServiceName;
    private String ServiceDescription;
    private double ServicePrice;
    
    
    //Setup Price
    private Locale locale ;
    private NumberFormat currencyFormater;
    public Service()
    {
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
    }
    
    @Override
    public String toString()
    {
        return this.ServiceName + " (ID : " + this.ID + " )";
    }
    
    public Service(Service service)
    {
        this.ID = service.ID;
        this.ServiceName = service.ServiceName;
        this.ServiceDescription = service.ServiceDescription;
        this.ServicePrice = service.ServicePrice;
        
        this.locale = new Locale("en", "US");
        this.currencyFormater = NumberFormat.getCurrencyInstance(locale);
        this.currencyFormater.setMaximumFractionDigits(0);
    }
    
    public Object[] toRowTable(EventAction event) {
        return new Object[]{this.ID, this.ServiceName, this.ServiceDescription, this.currencyFormater.format(this.ServicePrice), new ModelThreeAction(this, event)};
    }
    
    public Service(Integer ID, String Name)
    {
        this.ID = ID;
        this.ServiceName = Name;
    }

    /**
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * @return the ServiceName
     */
    public String getServiceName() {
        return ServiceName;
    }

    /**
     * @param ServiceName the ServiceName to set
     */
    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    /**
     * @return the ServiceDescription
     */
    public String getServiceDescription() {
        return ServiceDescription;
    }

    /**
     * @param ServiceDescription the ServiceDescription to set
     */
    public void setServiceDescription(String ServiceDescription) {
        this.ServiceDescription = ServiceDescription;
    }

    /**
     * @return the ServicePrice
     */
    public double getServicePrice() {
        return ServicePrice;
    }

    /**
     * @param ServicePrice the ServicePrice to set
     */
    public void setServicePrice(double ServicePrice) {
        this.ServicePrice = ServicePrice;
    }
    
    
}
