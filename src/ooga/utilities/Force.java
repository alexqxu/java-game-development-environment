package ooga.utilities;

public class Force {


    private double xForce;
    private double yForce;
    private String type;

    public Force(double fx, double fy){
        xForce=fx;
        yForce=fy;
        type="Constant";
    }
    public double getFX(){
        return xForce;
    }

    public double getFY(){
        return yForce;
    }

    public void setxForce(double xForce) {
        this.xForce = xForce;
    }

    public void setyForce(double yForce) {
        this.yForce = yForce;
    }

    /**
     *
     * @param type of force which is either Constant or Hit
     */
    public void setForceType(String type){
        this.type=type;
    }
    public String getForceType(){
        return type;
    }

}
