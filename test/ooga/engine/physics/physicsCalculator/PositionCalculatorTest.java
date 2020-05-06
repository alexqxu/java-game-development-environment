package ooga.engine.physics.physicsCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionCalculatorTest {

    //int previousX, double velocityX, double xAcceleration
    private AccelerationCalculator ac=new AccelerationCalculator();
    private VelocityCalculator vc=new VelocityCalculator();

    private PositionCalculator pc= new PositionCalculator();

    @Test
    void getX() {
        int previousX=10;
        double velocityX=12;
        double xAcceleration=10;

        assertEquals(17, pc.getX(previousX,velocityX,xAcceleration));
        assertEquals(27, pc.getX(previousX,velocityX,-xAcceleration));
        assertEquals(-7, pc.getX(previousX,-velocityX,xAcceleration));
        assertEquals(3, pc.getX(previousX,-velocityX,-xAcceleration));
        assertEquals(-3, pc.getX(-previousX,velocityX,xAcceleration));
        assertEquals(7, pc.getX(-previousX,velocityX,-xAcceleration));
        assertEquals(-27, pc.getX(-previousX,-velocityX,xAcceleration));
        assertEquals(-17, pc.getX(-previousX,-velocityX,-xAcceleration));

    }

    @Test
    void getY() {
        int previousY=10;
        double velocityY=12;
        double yAcceleration=10;

        assertEquals(17, pc.getY(previousY,velocityY,yAcceleration));
        assertEquals(27, pc.getY(previousY,velocityY,-yAcceleration));
        assertEquals(-7, pc.getY(previousY,-velocityY,yAcceleration));
        assertEquals(3, pc.getY(previousY,-velocityY,-yAcceleration));
        assertEquals(-3, pc.getY(-previousY,velocityY,yAcceleration));
        assertEquals(7, pc.getY(-previousY,velocityY,-yAcceleration));
        assertEquals(-27, pc.getY(-previousY,-velocityY,yAcceleration));
        assertEquals(-17, pc.getY(-previousY,-velocityY,-yAcceleration));
    }
}