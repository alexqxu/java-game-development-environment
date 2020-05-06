/**
 * Main class for Ooga game project.
 */
package ooga;
/**
 * Feel free to completely change this code or delete it entirely.
 */
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.ViewManager;

public class Main extends Application {
    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ViewManager viewManager = new ViewManager(primaryStage);
    }
}

