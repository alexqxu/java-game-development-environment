package ooga.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ooga.model.Entity;
import ooga.utilities.Coordinate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

public class CameraView {
    private SimpleDoubleProperty xProperty;
    private SimpleDoubleProperty yProperty;
    private SimpleDoubleProperty backgroundWidth;
    private SimpleDoubleProperty backgroundHeight;
    private ReadOnlyDoubleProperty sceneWidth;
    private ReadOnlyDoubleProperty sceneHeight;
    private Pane gamePane;
    private Consumer<ImageView> bindWindow;
    private double xRatio;
    private double yRatio;
    private double originalX;
    private double originalY;

    public CameraView(Entity player, ReadOnlyDoubleProperty sceneHeight, ReadOnlyDoubleProperty sceneWidth ){
        xProperty = new SimpleDoubleProperty();
        yProperty = new SimpleDoubleProperty();
        xProperty.setValue(player.getParameterController().getCoordinate().getX());
        yProperty.setValue(player.getParameterController().getCoordinate().getY());
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        xRatio =1;
        yRatio = 1;
        createBindingConsumer();
    }
    public Consumer<ImageView> getBindingCosumer(){ return bindWindow;}

    private void createBindingConsumer(){
        bindWindow = entity-> {
            sceneHeight.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double ratio = newValue.doubleValue() / oldValue.doubleValue();
                    entity.setFitHeight(entity.getFitHeight()*ratio);
                    entity.setY(entity.getY()*ratio);
                }
            });
            sceneWidth.addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    double ratio = newValue.doubleValue() / oldValue.doubleValue();
                    entity.setX(entity.getX()*ratio);
                    entity.setFitWidth(entity.getFitWidth()*ratio);
                    entity.setY(entity.getY()*ratio);
                }
            });
        };
    }
    public void handleCamera(Pane rightPane, ImageView playerDisplay, ImageView background) {
        bindBackgroundImage(background);
        originalX = sceneWidth.doubleValue();
        originalY = sceneHeight.doubleValue();
        sceneWidth = rightPane.widthProperty();
        sceneHeight = rightPane.heightProperty();
        setPaneClip(rightPane, playerDisplay);
    }

    private void setPaneClip(Pane rightPane, ImageView playerDisplay) {
        Rectangle clip = createClip(playerDisplay);
        rightPane.setClip(clip);
        rightPane.translateXProperty().bind(clip.xProperty().multiply(-1));
        rightPane.translateYProperty().bind(clip.yProperty().multiply(-1));
    }

    private Rectangle createClip(ImageView playerDisplay) {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(sceneWidth);
        clip.heightProperty().bind(sceneHeight);
        clip.xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerDisplay.getX() - sceneWidth.getValue() / 2, 0,
                        backgroundWidth.doubleValue() - sceneWidth.getValue()),
                xProperty, sceneWidth));
        clip.yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(playerDisplay.getY() - sceneHeight.getValue() / 2, 0,
                        backgroundHeight.doubleValue() - sceneHeight.getValue()),
                yProperty, sceneHeight));
        return clip;
    }

    private double clampRange(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public ImageView createPlayerDisplay(Entity player) throws FileNotFoundException {
        ImageView playerDisplay = new ImageView(
                new Image(new FileInputStream(player.getParameterController().getImage())));
        playerDisplay.setX(player.getParameterController().getCoordinate().getX());
        playerDisplay.setY(player.getParameterController().getCoordinate().getY());
        player.getDynamicEntityCoordinate().addListener(new ChangeListener<Coordinate>() {
            @Override
            public void changed(ObservableValue<? extends Coordinate> observable, Coordinate oldValue,
                                Coordinate newValue) {
                playerDisplay.setX(clampRange(newValue.getX() * xRatio, 0, backgroundWidth.doubleValue()));
                playerDisplay.setY(clampRange(newValue.getY() * yRatio, 0, backgroundHeight.doubleValue()));
                xProperty.setValue(clampRange(newValue.getX() * xRatio, 0, backgroundWidth.doubleValue()));
                yProperty.setValue(clampRange(newValue.getY() * yRatio, 0, backgroundHeight.doubleValue()));
                String str = xProperty.toString();
                String str2 = yProperty.toString();
            }
        });
        sceneHeight.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                yRatio = newValue.doubleValue() / originalY;
                playerDisplay.setFitHeight(playerDisplay.getFitHeight()*yRatio);
                playerDisplay.setY((playerDisplay.getY() / oldValue.doubleValue()) * newValue.doubleValue());
            }
        });
        sceneWidth.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                xRatio = newValue.doubleValue() / originalX;
                playerDisplay.setX(playerDisplay.getX()*(newValue.doubleValue() / oldValue.doubleValue()));
            }
        });
        return playerDisplay;
    }

    public void bindBackgroundImage(ImageView background) {
        backgroundWidth = new SimpleDoubleProperty(background.getImage().getWidth());
        backgroundHeight = new SimpleDoubleProperty(background.getImage().getHeight());
        background.setFitWidth(background.getImage().getWidth());
        background.setFitHeight(background.getImage().getHeight());
        updateSize(background);
    }

    private void updateSize(ImageView background) {
        sceneHeight.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() > background.getImage().getHeight()){
                    background.setFitHeight(newValue.doubleValue());
                    backgroundHeight.set(newValue.doubleValue());
               }
            }
        });
        sceneWidth.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() > background.getImage().getWidth()){
                    background.setFitWidth(newValue.doubleValue());
                    backgroundWidth.set(background.getFitWidth());
                }
            }
        });
    }
}
