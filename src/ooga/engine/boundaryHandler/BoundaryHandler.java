package ooga.engine.boundaryHandler;


/**
 * This class handles the boundary interactions with entities
 */
public final class BoundaryHandler {


    /*
    Have multiple boundary conditions here, through helper classes.
    load in the right sub component through a properties file.
     */

    private BoundaryHandler(){
        throw new AssertionError();
    }

    public static int checkX(int oldX){
        return oldX;
    }

    public static int checkY(int oldY){
        return oldY;
    }
}
