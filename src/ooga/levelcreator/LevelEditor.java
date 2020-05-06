package ooga.levelcreator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import ooga.parser.components.ObjectsInfoParser;
import ooga.parser.helperObjects.GameObject;
import ooga.parser.helperObjects.ObjectBuffer;


public class LevelEditor extends Application {
    private Scene scene;
    private GridPane grid;
    private Map<String, List<Pair<Integer, Integer>>> coordinates;
    private GameObject selectedObject;
    private static final String BRICK = "Brick";
    private static final String DATA_FOLDER="data/Games/";
    private List<ImageView> images;
    private ImageView background;
    private ObjectsInfoParser objectsInfoParser;
    private String gameName;
    private String backgroundPath;

    public LevelEditor(String gameName, String backgroundPath) throws FileNotFoundException {
        this.gameName= gameName;
        objectsInfoParser = new ObjectsInfoParser(String.format("data/Games/%s/objects.xml", gameName));
        this.backgroundPath = backgroundPath;
        background = new ImageView(new Image(new FileInputStream(backgroundPath)));
    }

    public static void main (String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        images=new ArrayList<>();
        coordinates = new HashMap();
        background.setFitHeight(background.getImage().getHeight());
        background.setFitWidth(background.getImage().getWidth());
        buildScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buildScene() throws FileNotFoundException {
        SplitPane split = new SplitPane();
        ScrollPane scroll = new ScrollPane();
        ScrollPane leftScroll = new ScrollPane();
        scene = new Scene(split, 800, 600);
        Group root = new Group();
        Pane left = new Pane();
        VBox vbox = getButtons();
        left.getChildren().add(vbox);
        split.setDividerPositions(0.35f, 0.6f);
        leftScroll.setContent(left);
        split.getItems().addAll(leftScroll, scroll);
        SplitPane.setResizableWithParent(left, Boolean.FALSE);
        grid = createGrid();
        root.getChildren().add(background);
        root.getChildren().add(grid);
        scroll.setContent(root);
        scroll.setVvalue(1);
        scene.getStylesheets().add("ooga/view/resources/css/defaultlayout.css");
    }

    private VBox getButtons() {
        Button reset = new Button("Reset");
        reset.setOnAction(e->reset());
        Button saveXML = new Button("Write XML");
        saveXML.setOnAction(e-> {
            try {
                saveXML();
            } catch (ParserConfigurationException ex) {
            }
        });
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.getChildren().addAll(reset, saveXML, getObjects());
        return vbox;
    }

    private void saveXML() throws ParserConfigurationException {
        try{
            XMLWriter xmlWriter = new XMLWriter();
            xmlWriter.createXML(coordinates, backgroundPath, gameName);
        }catch(ParserConfigurationException e){
            throw new ParserConfigurationException("Unable to parse");
        }


    }

    private Rectangle createRectangle(int i, int j){
        Rectangle rect = new Rectangle(0, 0,30,30);
        rect.setStyle("-fx-border-color: black;");
        rect.setFill(Color.TRANSPARENT);
        rect.setOnMouseClicked(e->{
            int row = GridPane.getRowIndex(rect);
            int col = GridPane.getColumnIndex(rect);
            try {
                grid.add(createImage(col, row), col, row, selectedObject.getWidth(), selectedObject.getHeight());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException("Unable to find file");
            }
            coordinates.putIfAbsent(selectedObject.getName(), new ArrayList<>());
            coordinates.get(selectedObject.getName()).add(new Pair(col, row));
        });
        return rect;
    }

    private GridPane createGrid() throws FileNotFoundException {
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        for (int i=0; i<background.getFitWidth()/30; i++){
            for (int j=0; j< background.getFitHeight()/30; j++){
                grid.add(createRectangle(i, j), i, j);
            }
        }
        return grid;
    }

    private ImageView createImage(int col, int row) throws FileNotFoundException {
        Image image= new Image(new FileInputStream(createImagePath()));
        ImageView im = new ImageView(image);
        if (row!=scene.getHeight()/30 -1)
            images.add(im);
        im.setOnMouseClicked(e->{
            im.setImage(null);
            coordinates.get(selectedObject.getName()).remove(new Pair(col,row));
        });
        im.setFitWidth(30*selectedObject.getWidth());
        im.setFitHeight(30*selectedObject.getHeight());
        return im;
    }

    private void reset(){
        removeImages();
        removeSelectedItem();
    }

    private void removeImages() {
        for (ImageView im: images){
            im.setVisible(false);
            //im.setImage(null);
        }
    }

    private void removeSelectedItem() {
        for (int i=0; i<scene.getWidth()/30 + 40; i++){
            for (int j=0; j< scene.getHeight()/30; j++){
                coordinates.get(selectedObject.getName()).remove(new Pair(i ,j));
            }
        }
    }

    private HBox getObjects(){
        HBox toggleButtons = new HBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        ObjectBuffer objects = objectsInfoParser.getObjects();
        for (int i=0; i<objects.size(); i++) {
            GameObject gameObject = objects.get(i);
            ToggleButton button = new ToggleButton(gameObject.getName());
            button.setToggleGroup(toggleGroup);
            button.setOnAction(e->{
                selectedObject = gameObject;
            });
            toggleButtons.getChildren().add(button);
        }
            selectedObject = objects.get(0);
        return toggleButtons;
    }

    private String createImagePath(){ return DATA_FOLDER+gameName+"/"+selectedObject.getImagePath();}
}
