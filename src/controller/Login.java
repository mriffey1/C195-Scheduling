package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;

import static DAO.UserDAO.*;

public class Login implements Initializable {

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

    public void actionLoginButton(ActionEvent actionEvent) throws IOException, SQLException {

        String User_Name = txtFieldUserName.getText();
        String Password = txtFieldUserPassword.getText();


        String filename = "login_activity.txt", items;
        FileWriter fwritter = new FileWriter(filename, true);
        Scanner keyboard = new Scanner(System.in);
        PrintWriter pwVariable = new PrintWriter(fwritter);


        if (User_Name.isEmpty() || User_Name.isBlank()) {
            System.out.println("Error username blank");
        } else if (!passwordValidation(Password) && !usernameValidation((User_Name))) {
            helper.ErrorMsg.getError(3);

        } else if (Password.isEmpty() || Password.isBlank()) {
            helper.ErrorMsg.getError(2);
            fwritter.write(User_Name + " has failed login due to password being blank " + new java.util.Date());
            fwritter.write("\n");
            fwritter.close();

        } else if (!usernameValidation(User_Name)) {
            helper.ErrorMsg.getError(1);
            // System.out.println("Test");

        } else if (!passwordValidation(Password)) {
            helper.ErrorMsg.getError(2);

        } else if (userLogin(User_Name, Password)) {

            helper.ErrorMsg.confirmation(1);
            FXMLLoader loader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Customers.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            fwritter.write(User_Name + " has successfully logged on " + new java.util.Date());
            fwritter.write("\n");
            fwritter.close();
        } else helper.ErrorMsg.getError(2);

    }

    public void actionCancelButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING, langBundle.getString("Cancel"));
        alert.setTitle("Exit Application");
        alert.setHeaderText(langBundle.getString("Areyousureyouwanttoexit?"));
        alert.setContentText(langBundle.getString("PressOKtoexitorpressCanceltostay."));
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