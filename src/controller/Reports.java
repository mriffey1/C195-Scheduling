package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class Reports {
    public TableColumn appointTotalType;
    public TableColumn appointTypeTotal;
    public TableColumn appointMonth;
    public TableColumn appointMonthTotal;
    public Tab contactSchedule;
    public TableColumn appointId;
    public TableColumn appointTitle;
    public TableColumn appointDescription;
    public TableColumn appointContact;
    public TableColumn appointType;
    public TableColumn appointStart;
    public TableColumn appointEnd;
    public TableColumn appointCustId;
    public TableColumn appointUserId;
    public ComboBox contactCombo;
    public TableColumn appointCountry;
    public TableColumn appointCountryTotal;
    public Button backToMenu;
    public Tab appointCountryTab;
    public Tab contactScheduleTab;
    public Tab appointTotalTab;

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
