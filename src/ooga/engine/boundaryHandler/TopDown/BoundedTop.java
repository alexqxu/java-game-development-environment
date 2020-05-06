package ooga.engine.boundaryHandler.TopDown;

import java.util.ResourceBundle;

/**
 * Defines the boundary conditions for Bounded Top.
 */
public class BoundedTop {
    private static final String FILE_PATH="resources.entityProperties.BoundaryHandler";
    private ResourceBundle textForFactory = java.util.ResourceBundle.getBundle(FILE_PATH);

    public BoundedTop(){

    }

    public int checkAndUpdate(int y){
        return y;
    }
}
