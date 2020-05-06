package ooga.utilities;

public class Velocity {

    private double xVelocity;
    private double yVelocity;

    public Velocity(double xVelocity, double yVelocity){
        this.xVelocity=xVelocity;
        this.yVelocity=yVelocity;
    }
    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }
}
