/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Table;

import Model.User;

/**
 *
 * @author couni
 */
public interface EventAction {

    public void delete(User user, int row);

    public void update(User user, int row);
}

