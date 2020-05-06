package ooga.utilities;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class FileManager {
    private static final String NAME = "Open File";
    private static final String USER_DIRECTORY = "user.dir";
    private FileChooser fileChooser;

    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle(NAME);
        // pick a reasonable place to start searching for files
        result.setInitialDirectory(new File(System.getProperty(USER_DIRECTORY)));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
    public String getFileFromChooser(String extension){
        fileChooser= makeChooser(extension);
        Stage stage = new Stage();
        File dataFile = fileChooser.showOpenDialog(stage);
        try {
            return dataFile.getPath();
        } catch(RuntimeException e){
            showError("No file choosen");
            return null;
        }
    }
    public String getFileFromChooser(){
        fileChooser= new FileChooser();
        Stage stage = new Stage();
        File dataFile = fileChooser.showOpenDialog(stage);
        try {
            return dataFile.getPath();
        } catch(RuntimeException e){
            showError("No file choosen");
            return null;
        }
    }
    public Collection<String> getFiles(String path) {
        File directoryPath = new File(path);
        List<String> files =new ArrayList<>();
        for (String s : directoryPath.list()) {
            if (isFile(path, s))
                files.add(s);
        }
        Collections.sort(files);
        return files;
    }

    public Collection<String> getDirectories(String path){
        File directoryPath = new File(path);
        List<String> files =new ArrayList<>();
        for (String s : directoryPath.list()) {
            if (isDirectory(path, s))
                files.add(s);
        }
        Collections.sort(files);
        return files;
    }

    private boolean isFile(String path, String file) {
        return new File(path+file).isFile();
    }

    private boolean isDirectory(String path, String file) {
        return new File(path+file).isDirectory();
    }

    private void showError (String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
