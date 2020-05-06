package ooga.utilities;

public class Acceleration {
    private double xAcceleration;
    private double yAcceleration;
    public Acceleration(double aX, double aY){
        xAcceleration=aX;
        yAcceleration=aY;
    }

    public double getXAcceleration(){
        return xAcceleration;
    }
    public double getYAcceleration(){
        return yAcceleration;
    }
}

