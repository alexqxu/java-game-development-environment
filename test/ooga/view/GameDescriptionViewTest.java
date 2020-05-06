package ooga.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import ooga.Main;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;
import util.DukeApplicationTest;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class GameDescriptionViewTest extends DukeApplicationTest {
    private Button myButton;
    private TextField myTextField;
    private ComboBox<String> myCombo;

    @BeforeEach
    public void setUp() throws Exception {
        // start GUI new for each test
        launch(Main.class);
        // different ways to find elements in GUI
        // by ID
        myTextField = lookup(".text-field").queryAs(TextField.class);
        // by being the only one of its kind within another element
        myCombo = lookup(".combo-box").queryComboBox();
        // by complete text in button
        myButton = lookup(".button").queryAs(Button.class);

        myTextField.clear();
    }

    @Test
    public void testComboBox() {
        String expected = "Spanish";
        select(myCombo, expected);
        assertEquals(expected, myCombo.getValue());
    }

    @Test
    void testComboBoxAction() {
        String expected = "Spanish";
        select(myCombo, "Spanish");
        assertEquals("Juega", myButton.getText());
    }

    @Test
    void testTextField() {
        String expected = "ENTER test!";
        clickOn(myTextField).write(expected).write(KeyCode.ENTER.getChar());
        assertEquals(expected, myTextField.getText());


    }

}