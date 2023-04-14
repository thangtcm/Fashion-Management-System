/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DatabaseAccessObject_DAO.Service_Dao;
import DatabaseAccessObject_Impl.Service_DaoImpl;
import Swing.Table.EventAction;
import Swing.Table.ModelAction;
import java.util.Date;

/**
 *
 * @author couni
 */
public class PatientService {
    private Integer ID;
    private Integer service;
    private Integer billService;
    private Date StartTime;
    private Date EndTime;
    private String Description;
    private String Result;
    private String Note;
    private double Price;
    
    private Service serivces;
    

    public PatientService()
    {
        
    }
    
    public Object[] toRowTable(EventAction event) {
        Service_Dao service_Dao = new Service_DaoImpl();
        
        return new Object[]{this.ID, service_Dao.getService(this.service).getServiceName() , this.StartTime, this.EndTime, this.Result, new ModelAction(this, event)};
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
     * @return the StartTime
     */
    public Date getStartTime() {
        return StartTime;
    }

    /**
     * @param StartTime the StartTime to set
     */
    public void setStartTime(Date StartTime) {
        this.StartTime = StartTime;
    }

    /**
     * @return the EndTime
     */
    public Date getEndTime() {
        return EndTime;
    }

    /**
     * @param EndTime the EndTime to set
     */
    public void setEndTime(Date EndTime) {
        this.EndTime = EndTime;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Result
     */
    public String getResult() {
        return Result;
    }

    /**
     * @param Result the Result to set
     */
    public void setResult(String Result) {
        this.Result = Result;
    }

    /**
     * @return the Note
     */
    public String getNote() {
        return Note;
    }

    /**
     * @param Note the Note to set
     */
    public void setNote(String Note) {
        this.Note = Note;
    }

    /**
     * @return the service
     */
    public Integer getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Integer service) {
        this.service = service;
    }

    /**
     * @return the billService
     */
    public Integer getBillService() {
        return billService;
    }

    /**
     * @param billService the billService to set
     */
    public void setBillService(Integer billService) {
        this.billService = billService;
    }

    /**
     * @return the Price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }

    public Service getSerivces() {
        return serivces;
    }

    public void setSerivces(Service serivces) {
        this.serivces = serivces;
    }
    
    
}
