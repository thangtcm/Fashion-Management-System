/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author couni
 */
public class PhoneCheck {
    String phoneNumber ="^\\{10,11}$";
    public boolean isValidPhone(String Phone)
    {
        return Phone.matches(phoneNumber);
    }
}
