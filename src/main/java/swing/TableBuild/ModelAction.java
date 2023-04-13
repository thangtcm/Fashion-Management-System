/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing.TableBuild;


/**
 *
 * @author couni
 */
public class ModelAction<T> {
    private T object;
    private EventAction event;

    public ModelAction(T object, EventAction event) {
        this.object = object;
        this.event = event;
    }
    public ModelAction() {
    }
    /**
     * @return the event
     */
    public EventAction getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(EventAction event) {
        this.event = event;
    }

    /**
     * @return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(T object) {
        this.object = object;
    }
}
