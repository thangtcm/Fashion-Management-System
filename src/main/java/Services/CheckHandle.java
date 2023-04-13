/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.util.regex.Pattern;

/**
 *
 * @author couni
 */
public class CheckHandle {
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    public static boolean isValidPhone(String Phone)
    {
        String regex = "0[0-9]{9}|84[0-9]{9}";
        
        return Pattern.matches(regex, Phone);
    }
}
