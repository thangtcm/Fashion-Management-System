/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;

/**
 *
 * @author Huynh
 */
public class Convert {

    public static java.sql.Date convertDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    public String convertName(String name){
        name = name.trim().toLowerCase();
        while (name.contains("  ")) {
            name = name.replaceAll("  ", " ");
        }
        String[] arr = name.split(" ");
        name = "";
        for (String arr1 : arr) {
            name += arr1.replaceFirst(arr1.substring(0, 1), arr1.substring(0, 1).toUpperCase()) + " ";
        }
        return name.trim();
    }
    
    public static void main(String[] args) {
        Convert con = new Convert();
        System.out.println(con.convertName(""));
    }
}
