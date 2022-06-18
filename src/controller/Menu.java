package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Menu {

    /**
     * Action event for button Customer that will redirect to the Customers screen.
     * @param actionEvent test
     * @throws IOException test
     */
    public void menuCustomer(ActionEvent actionEvent) throws IOException {
    //    FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Customers.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Action event for button Appointment that will redirect to the Appointments screen.
     * @param actionEvent test
     * @throws IOException test
     */
    public void menuAppoint(ActionEvent actionEvent) throws IOException {
      //  FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Action event for button Report that will redirect to the Reports screen.
     * @param actionEvent
     * @throws IOException
     */
    public void menuReport(ActionEvent actionEvent) throws IOException {
     //   FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Reports.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Action event for exit button on the main menu that will close the program.
     * @param actionEvent
     */
    public void menuExit(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
