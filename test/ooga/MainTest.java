package ooga;
/**
 * Class to test Main Application when run
 * @Author Franklin Boampong
 */


import java.util.concurrent.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class MainTest extends DukeApplicationTest {
    private Button myButton;
    private TextField myTextField;
    private ComboBox<String> myCombo;

  private String id = "11";
  public static final String TEXT_1 = "Franklin";
  public static final String TEXT_2 = "fb@gmail.com";
  public static final String TEXT_3 = "1234";
  private Button mySignUpButton;
  private Button myLogInButton;
  private TextField nameTextField;
  private TextField emailTextField;
  private TextField passwordTextField;

  @BeforeEach
  public void setUp() throws Exception {
    launch(Main.class);
    mySignUpButton = lookup("#signup-button").queryAs(Button.class);
    myLogInButton = lookup("#login-button").queryAs(Button.class);
    nameTextField = lookup("#name-field").queryAs(TextField.class);
    emailTextField = lookup("#email-field").queryAs(TextField.class);
    passwordTextField = lookup("#password-field").queryAs(TextField.class);
  }

  @Test
  void testSignUpButtonClickedMovesToLogIn() {
    assertTrue(!mySignUpButton.isDisabled());
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void testLogInButtonClicked() {
    assertTrue(!myLogInButton.isDisabled());
    sleep(1, TimeUnit.SECONDS);
  }

  @Test
  void testTextFields() {
    write(nameTextField, TEXT_1);
    sleep(1, TimeUnit.SECONDS);
    write(emailTextField, TEXT_2);
    sleep(1, TimeUnit.SECONDS);
    write(passwordTextField, TEXT_3);
    sleep(1, TimeUnit.SECONDS);

    assertTrue(nameTextField.getText().equals(TEXT_1));
    sleep(1, TimeUnit.SECONDS);
    assertTrue(emailTextField.getText().equals(TEXT_2));
    sleep(1, TimeUnit.SECONDS);
    assertTrue(passwordTextField.getText().equals(TEXT_3));
    sleep(1, TimeUnit.SECONDS);

  }

  @Test
  void testForInvalidInputTextField(){
    sleep(1, TimeUnit.SECONDS);
    clickOn(mySignUpButton);
    sleep(1, TimeUnit.SECONDS);
    assertEquals(((Label)lookup(".dialog-pane .content").query()).getText(), "Please enter your name");
  }

}