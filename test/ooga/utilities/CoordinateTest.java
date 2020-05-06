package ooga.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    private Coordinate cd=new Coordinate(10,20);

    @Test
    void getX() {
        assertEquals(10, cd.getX());
    }

    @Test
    void getY() {
        assertEquals(20, cd.getY());
    }
}