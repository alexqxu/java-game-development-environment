package ooga.engine.physics.subApplicators;

import java.util.ResourceBundle;

/**
 *This class has all the forces that the other subapplicator needs
 * @author  Abebe Amare
 */
public final class ForceValues {

    private static final String FILE_PATH="resources.engineProperties.ForceValues";
    private static ResourceBundle textForces = java.util.ResourceBundle.getBundle(FILE_PATH);

    private ForceValues(){
        throw new AssertionError();
    }

    public static double checkX(String forceType){
        return Double.parseDouble(textForces.getString(forceType+"X"));
    }

    public static double checkY(String forceType){
      return Double.parseDouble(textForces.getString(forceType+"Y"));

    }

}
