package ooga.parser;

import ooga.parser.components.CharacterInfoParser;
import ooga.parser.helperObjects.Character;
import ooga.parser.helperObjects.Control;
import ooga.parser.helperObjects.Interaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the character Parser functionality
 * @author Alex Xu
 */
public class CharacterParserTest {
    private static final String FILEPATH = "test/ooga/parser/characterConfig.xml";
    private CharacterInfoParser myParser;

    public CharacterParserTest(){
        myParser = new CharacterInfoParser(FILEPATH);
    }

    @Test
    void CharacterParsingTest(){
        List<Character> characters = new ArrayList<>();
        List<Control> controls = new ArrayList<>();
        controls.add(new Control("A", "Left"));
        controls.add(new Control("D", "Right"));
        controls.add(new Control("W", "Up"));
        controls.add(new Control("S", "Down"));

        List<Interaction> interactions = new ArrayList<>();
        interactions.add(new Interaction("Brick", "LEFT", "Right"));
        interactions.add(new Interaction("Brick", "RIGHT", "Left"));

        characters.add(new Character("Mario", "images/mario_running.gif", 7, 2, 1, 2, interactions, controls));

        assertEquals(characters,myParser.getCharacters());
    }
}
