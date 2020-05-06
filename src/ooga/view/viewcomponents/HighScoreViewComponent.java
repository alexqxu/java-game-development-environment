package ooga.view.viewcomponents;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ooga.controller.TopScorersData;
import ooga.controller.WinnerProfile;
import ooga.view.ViewActions;

import java.util.Collection;

public class HighScoreViewComponent extends Component{
    private TopScorersData topScorersData;
    private static final int NUMBER_OF_PLAYERS = 5;
    private TableView tableView;

    public HighScoreViewComponent(String componentName, SimpleStringProperty language, ViewActions viewActions) {
        super(componentName, language, viewActions);
        tableView = new TableView();
        TableColumn nameColumn = new TableColumn(getName("NameColumn"));
        TableColumn scoreColumn = new TableColumn(getName("ScoreColumn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<WinnerProfile,String>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<WinnerProfile, String>("score"));
        tableView.getColumns().addAll(nameColumn, scoreColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setId(getCssTag(componentName));
    }
    @Override
    public Pane getNode(){
        topScorersData = new TopScorersData(getGameName());
        //System.out.println(getGameName());
        Collection<WinnerProfile> list = topScorersData.getWinners(NUMBER_OF_PLAYERS);
        ObservableList<WinnerProfile> displayList = FXCollections.observableArrayList(list);
        tableView.setItems(displayList);
        getChildren().add(tableView);
        return this;
    }
}
