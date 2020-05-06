package ooga.controller;

import ooga.controller.keyboardInputHandler.InputStateMachine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Keyboard input state machine.
 * @author Alex Xu
 */
public class KeyinputStateMachineTest {
    private InputStateMachine stateMachine;

    public KeyinputStateMachineTest(){
        stateMachine = new InputStateMachine();
    }

    @Test
    void pressTest1(){
        stateMachine.pressKey("UP");
        List<String> newList = new ArrayList<>();
        newList.add("UP");
        assertEquals(newList,stateMachine.getActiveKeyList());
    }

    @Test
    void pressTest2(){
        stateMachine.pressKey("UP");
        stateMachine.pressKey("UP");
        stateMachine.pressKey("DOWN");
        stateMachine.pressKey("A");
        stateMachine.pressKey("VirtualLEFT");
        stateMachine.pressKey("DOWN");
        List<String> newList = new ArrayList<>();
        newList.add("UP");
        newList.add("DOWN");
        newList.add("A");
        newList.add("VirtualLEFT");
        assertEquals(newList,stateMachine.getActiveKeyList());
    }

    @Test
    void removeTest1(){
        stateMachine.pressKey("UP");
        stateMachine.releaseKey("UP");
        List<String> newList = new ArrayList<>();
        assertEquals(newList,stateMachine.getActiveKeyList());
    }

    @Test
    void removeTest2(){
        stateMachine.pressKey("UP");
        stateMachine.pressKey("UP");
        stateMachine.pressKey("DOWN");
        stateMachine.pressKey("A");
        stateMachine.releaseKey("UP");
        stateMachine.releaseKey("DOWN");
        stateMachine.releaseKey("DOWN");
        stateMachine.releaseKey("DOWN");
        stateMachine.releaseKey("B");
        stateMachine.pressKey("B");
        stateMachine.pressKey("VirtualLEFT");
        stateMachine.pressKey("DOWN");
        List<String> newList = new ArrayList<>();
        newList.add("A");
        newList.add("B");
        newList.add("VirtualLEFT");
        newList.add("DOWN");
        assertEquals(newList,stateMachine.getActiveKeyList());
    }
}
