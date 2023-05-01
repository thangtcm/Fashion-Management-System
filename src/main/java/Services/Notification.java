/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Enum.TypeNotification;
import dialog.Message;
import dialog.NotificationMessage;

/**
 *
 * @author couni
 */
public class Notification {
    public static boolean showMessage(String message, TypeNotification type) {
        NotificationMessage obj = new NotificationMessage(null, true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
    
    public static boolean showConfirm(String message, TypeNotification type) {
        Message obj = new Message(null, true);
        obj.showMessage(message, type);
        return obj.isOk();
    }
}
