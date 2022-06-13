package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {



    public void menuCustomer(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void menuAppoint(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void menuReport(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void menuExit(ActionEvent actionEvent) {
    }
}
