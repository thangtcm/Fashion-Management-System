/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author YAN
 */
public class ModelWork {

    /**
     * @return the idfile
     */
    public String getIdfile() {
        return idfile;
    }

    /**
     * @param idfile the idfile to set
     */
    public void setIdfile(String idfile) {
        this.idfile = idfile;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idpatient
     */
    public String getIdpatient() {
        return idpatient;
    }

    /**
     * @param idpatient the idpatient to set
     */
    public void setIdpatient(String idpatient) {
        this.idpatient = idpatient;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public ModelWork(String bN0032, String tran_Cao_Minh_Thang, String bA0032, String _Mar_2023, String _am, String status1) {
    }
    
    private String idfile;
    private String name;
    private String idpatient;
    private String date;
    private String time;
    private String status;
    public Object[] toDataTable() {
        return new Object[]{idfile, name, idpatient, date,time,status};
    }
}
