package ooga.engine.physics.physicsCalculator;

/**
 * This class calculates the acceleration of an entity based on the force exerted to the entity
 * @author  Abebe Amare
 */
public class AccelerationCalculator {

    private static final int MASS=10;
    public AccelerationCalculator(){

    }

    /**
     *
     *
     * @param initialXAcceleration the initial acceleration to the x direction
     * @param fx the force exerted to the x direction
     * @return the acceleration to the x direction
     */
    public double getAX(double initialXAcceleration, Double fx){

            return fx/MASS+initialXAcceleration;

    }

    /**
     *
     *
     * @param initialYAcceleration the initial velocity to the y direction
     * @param fy the force exerted to the y direction
     * @return rhe acceleration to the y direction
     */
    public double getAY(double initialYAcceleration, Double fy){

            return fy/MASS+initialYAcceleration;

    }
}
