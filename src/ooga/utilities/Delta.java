package ooga.utilities;



public class Delta {

    private int moveX;
    private int moveY;

    /**
     *
     * @param deltaX the change in coordinate in the x direction
     * @param deltaY the change in coordinate in the y direction
     */
    public Delta(int deltaX, int deltaY){
        this.moveX=deltaX;
        this.moveY=deltaY;
    }

    /**
     *
     * @return the amount it needs to move in the x direction
     */
    public int getMoveX(){
        return moveX;
    }

    /**
     *
     * @return the amount it needs to move in the y direction
     */
    public int getMoveY(){
        return moveY;
    }




}
