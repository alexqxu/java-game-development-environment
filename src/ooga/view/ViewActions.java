/**
 * Class to set up view actions
 */
package ooga.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ViewActions {

    private List<PropertyChangeListener> listeners = new ArrayList<>();
    private String data;

    /**
     * method to help add listeners ton property change listeners in view
     * @param newListener is the listener to be attached
     */
    public void addChangeListener(PropertyChangeListener newListener) {
        listeners.add(newListener);
    }

    private void notifyListeners(String property, String oldValue, String newValue) {
        for (PropertyChangeListener name : listeners) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
        }
    }

    /**
     *
     * @param value
     */
    public void handleSceneChange(String value) {
        notifyListeners("Change Screen", this.data, this.data = value);
    }
    public void handleGoDescriptionScene(String game){ notifyListeners("Change Screen", game, "goDescriptionScene");}
}
