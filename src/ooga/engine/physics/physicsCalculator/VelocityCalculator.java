package ooga.engine.physics.physicsCalculator;

/**
 * This class calculates the final velocity of an entity based on the force exerted on it
 * @author  Abebe Amare
 */
public class VelocityCalculator {

    private static final int TIME=1;
    public VelocityCalculator(){

    }

    /**
     *
     *
     *
     * @param aX the acceleration of an entity to the x direction
     * @return the final x velocity of the entity based on the current acceleration in the x direction
     */
    public double getXVelocity(double aX, double initialXVelocity){

            return aX*TIME+initialXVelocity;


    }

    /**
     *
     *
     * @param initialYVelocity the initial velocity to the y direction
     * @param aY the acceleration of an entity to the y direction
     * @return the final y velocity of the entity based on the acceleration and the initial velocity
     */
    public double getYVelocity(double initialYVelocity, double aY)
    {

            return aY*TIME+initialYVelocity;


    }
}
