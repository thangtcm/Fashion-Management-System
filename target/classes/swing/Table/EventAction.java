/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing.Table;


/**
 *
 * @author couni
 */
public interface EventAction {

    public <T> void delete(T object , int row);

    public <T> void update(T object, int row);
    
    public <T> void view(T object, int row);
}

