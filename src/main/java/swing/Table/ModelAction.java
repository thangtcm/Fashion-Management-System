/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.Table;

import Model.Order;
import Model.User;

/**
 *
 * @author couni
 */
public class ModelAction {
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public EventAction getEvent() {
        return event;
    }

    public void setEvent(EventAction event) {
        this.event = event;
    }

    public ModelAction(User user, EventAction event) {
        this.user = user;
        this.event = event;
    }

    public ModelAction(Order order, EventAction event) {
        this.order = order;
        this.event = event;
    }
    public ModelAction() {
    }

    private User user;
    private Order order;
    private EventAction event;
}
