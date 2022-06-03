package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class login implements Initializable {

    public Label labelLocation;
    public TextField txtFieldUserName;
    public TextField txtFieldUserPassword;
    public Label labelUserName;
    public Label labelUserPassword;
    public Button loginButton;
    public Button cancelButton;
    public Label loginTitle;

    Stage stage;
    ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    public void actionLoginButton(ActionEvent actionEvent) {

    }

    public void actionCancelButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, langBundle.getString("Cancel"));
        alert.setTitle("Exit Application");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Press OK to exit or press Cancel to stay.");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TimeZone userTimeZone = TimeZone.getDefault();
        String tz1 = userTimeZone.getID();
        labelLocation.setText(tz1);

        labelUserName.setText(langBundle.getString("Username"));
        labelUserPassword.setText(langBundle.getString("Password"));
        loginTitle.setText(langBundle.getString("SchedulingApplication"));
        loginButton.setText(langBundle.getString("Login"));
        cancelButton.setText(langBundle.getString("Cancel"));



    }
}