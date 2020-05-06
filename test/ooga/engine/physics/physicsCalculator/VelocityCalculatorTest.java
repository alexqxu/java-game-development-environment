package ooga.engine.physics.physicsCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VelocityCalculatorTest {

    private VelocityCalculator vc=new VelocityCalculator() ;
    @Test
    void getXVelocity() {

        double aX=5;
        double initialVX=3;
        assertEquals(8, vc.getXVelocity(aX,initialVX));
        assertEquals(2, vc.getXVelocity(aX,-initialVX));
        assertEquals(-2, vc.getXVelocity(-aX,initialVX));
        assertEquals(-8, vc.getXVelocity(-aX,-initialVX));

    }

    @Test
    void getYVelocity() {
        double aY=5;
        double initialVY=5;
        assertEquals(10, vc.getYVelocity(aY,initialVY));
        assertEquals(0, vc.getYVelocity(aY,-initialVY));
        assertEquals(0, vc.getYVelocity(-aY,initialVY));
        assertEquals(-10, vc.getYVelocity(-aY,-initialVY));
    }
}