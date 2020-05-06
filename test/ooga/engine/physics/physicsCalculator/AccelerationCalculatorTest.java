package ooga.engine.physics.physicsCalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccelerationCalculatorTest {

    private AccelerationCalculator ac=new AccelerationCalculator();
    @Test
    void getAX() {
       // double initialXAcceleration, Double fx
        double fx=10;
        double initialXAcceleration=5;

        assertEquals(15,ac.getAX(initialXAcceleration, fx));
        assertEquals(5, ac.getAX(-initialXAcceleration, fx));
        assertEquals(-15, ac.getAX(-initialXAcceleration, -fx));
        assertEquals(-5, ac.getAX(initialXAcceleration, -fx));
    }

    @Test
    void getAY() {
        double fy=10;
        double initialYAcceleration=5;

        assertEquals(15,ac.getAY(initialYAcceleration, fy));
        assertEquals(5, ac.getAY(-initialYAcceleration, fy));
        assertEquals(-15, ac.getAY(-initialYAcceleration, -fy));
        assertEquals(-5, ac.getAY(initialYAcceleration, -fy));
    }
}