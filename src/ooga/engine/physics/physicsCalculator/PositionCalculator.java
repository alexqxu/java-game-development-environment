package ooga.engine.physics.physicsCalculator;

/**
 * This class calculates the final position of an entity based on the velocity of an entity and the previous location
 * of an entity
 * @author  Abebe Amare
 */
public class PositionCalculator {
    private static final int TIME=1;
    public PositionCalculator(){

    }

    /**
     *
     * @param previousX the previous x location of the entity
     * @param velocityX the current x velocity of the entity
     * @return the final x position of the entity
     */
    public int getX(int previousX, double velocityX, double xAcceleration){

        return (int) (previousX+velocityX*TIME-(0.5)*Math.pow(TIME,2)*xAcceleration);

    }

    /**
     *
     * @param previousY the previous y location of the entity
     * @param velocityY the current y velocity of the entity
     * @return the final y position of the entity
     */
    public int getY(int previousY, double velocityY, double yAcceleration){

        return (int) (previousY+velocityY*TIME-(0.5)*Math.pow(TIME, 2)*yAcceleration);

    }
}
