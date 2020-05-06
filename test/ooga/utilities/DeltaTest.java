package ooga.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeltaTest {

    private Delta dt=new Delta(10,20);

    @Test
    void getMoveX() {
        assertEquals(10, dt.getMoveX());
    }

    @Test
    void getMoveY() {
        assertEquals(20, dt.getMoveY());
    }
}