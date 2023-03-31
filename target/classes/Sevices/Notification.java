/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sevices;

import dialog.NotificationMessage;

/**
 *
 * @author couni
 */
public class Notification {
    public boolean showMessage(String message, String TypeNotification) {
        NotificationMessage obj = new NotificationMessage(null, true);
        obj.showMessage(message, TypeNotification);
        return obj.isOk();
    }
}
