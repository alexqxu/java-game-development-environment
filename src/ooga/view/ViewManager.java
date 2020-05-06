/**
 * Class to handle scenes and decide which scene is shown at a time
 */
package ooga.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ViewManager implements PropertyChangeListener {

    private Stage primaryStage;
    private GameSelectionView myGameSelectionView;
    private GameDescriptionView myGameDescriptionView;
    private GameSignUpView myGameSignUpView;
    private GameLogInView myGameLogInView;
    public static final String DEFAULT_LANGUAGE = "English";
    private ViewActions viewActions;


    public ViewManager(Stage stage) throws IOException, SAXException, ParserConfigurationException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ooga");
        viewActions = new ViewActions();
        viewActions.addChangeListener(this);
        myGameSignUpView = new GameSignUpView(new SimpleStringProperty(DEFAULT_LANGUAGE), viewActions);
        myGameLogInView = new GameLogInView(new SimpleStringProperty(DEFAULT_LANGUAGE), viewActions);
        myGameSelectionView = new GameSelectionView(new SimpleStringProperty(DEFAULT_LANGUAGE), viewActions);
        myGameDescriptionView = new GameDescriptionView("SampleGame1", new SimpleStringProperty(DEFAULT_LANGUAGE), viewActions);
        primaryStage.setScene(myGameSignUpView.getScene());
        primaryStage.show();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String methodName = evt.getNewValue().toString();
        try {
            Method m = this.getClass().getDeclaredMethod(methodName, PropertyChangeEvent.class);
            m.invoke(this, evt);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void goSelectionScene(PropertyChangeEvent e) {
        primaryStage.setScene(myGameSelectionView.getScene());
    }

    public void goDescriptionScene(PropertyChangeEvent e) throws FileNotFoundException {
        myGameDescriptionView = new GameDescriptionView(e.getOldValue().toString(), new SimpleStringProperty(DEFAULT_LANGUAGE), viewActions);
        primaryStage.setScene(myGameDescriptionView.getScene());
    }

    public void goLoginScene(PropertyChangeEvent e) throws FileNotFoundException {
            primaryStage.setScene(myGameLogInView.getScene());
    }

    public void goSignUpScene(PropertyChangeEvent e) throws FileNotFoundException {
        primaryStage.setScene(myGameSignUpView.getScene());
    }
}
