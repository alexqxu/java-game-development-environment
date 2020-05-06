package ooga.model.backEndEntities;

import java.util.ResourceBundle;

/**
 *This class has all the forces that the other subapplicator needs
 * @author  Abebe Amare
 */
public final class EntityValues {

    private static final String FILE_PATH="resources.engineProperties.EntityValues";
    private static ResourceBundle textForces = java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String LIVE="Lives";
    private static final String SCORE="Scores";
    private static final String STATUS="Status";
    private static final String PLAYING="playing";
    private static final String DEAD="dead";

    private EntityValues(){
        throw new AssertionError();
    }

    public static int getLive(String entityName){
        return Integer.parseInt(textForces.getString(entityName+LIVE));
    }

    public static int getScore(String entityName){
        return Integer.parseInt(textForces.getString(entityName+SCORE));

    }
    public static String getStatus(String entityName){
        return (textForces.getString(entityName+STATUS));
    }
    public static String dead(){
        return DEAD;
    }
    public static String playing(){
        return PLAYING;
    }

}
