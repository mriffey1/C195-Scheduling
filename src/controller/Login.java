package controller;

import DAO.AppointmentDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static DAO.UserDAO.*;

/**
 * Login class
 *
 * @author Megan Riffey
 */

public class Login implements Initializable {

    public static Object userName;
    public Label labelLocation;
    public TextField txtFieldUserName;
    @FXML
    private PasswordField txtFieldUserPassword;
    public Label labelUserName;
    public Label labelUserPassword;
    public Button loginButton;
    public Button cancelButton;
    public Label loginTitle;

    Stage stage;
    ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    ObservableList<Appointment> appointment15List = AppointmentDAO.getAppointmentList();
    LocalDateTime currentTime = LocalDateTime.now();
    ZonedDateTime LDTConvert = currentTime.atZone(ZoneId.systemDefault());

public void loginActivity(String value) throws IOException {

    ZonedDateTime LDTUTC = LDTConvert.withZoneSameInstant(ZoneId.of("Etc/UTC"));
    String filename = "login_activity.txt", items;
    FileWriter fwritter = new FileWriter(filename, true);
    fwritter.write(value);
    fwritter.write("\n");
    fwritter.close();
}

    /**
     * Validates user login using username and password and displaying appropriate error or success messages and logging
     * the successful or unsuccessful login in the login_activity.txt file upon clicking the "Login" button.
     *
     */
    public void actionLoginButton(ActionEvent actionEvent) throws IOException, SQLException {
        String User_Name = txtFieldUserName.getText();
        String Password = txtFieldUserPassword.getText();

        LocalDateTime currentTimePlus15 = LocalDateTime.now().plusMinutes(15);
        ZonedDateTime LDTConvert = currentTime.atZone(ZoneId.systemDefault());
        ZonedDateTime LDTUTC = LDTConvert.withZoneSameInstant(ZoneId.of("Etc/UTC"));


        if (User_Name.isEmpty() || User_Name.isBlank()) {
            helper.ErrorMsg.getError(5);
            System.out.println("Username is blank");

        } else if (!passwordValidation(Password) && !usernameValidation((User_Name))) {
            helper.ErrorMsg.getError(3);
            System.out.println("Everything is incorrect");

        } else if (Password.isEmpty() || Password.isBlank()) {
            helper.ErrorMsg.getError(6);

        } else if (!usernameValidation(User_Name)) {
            helper.ErrorMsg.getError(1);
            System.out.println("Incorrect/Invalid Username");
            loginActivity("User " + " has failed login due to an incorrect USERNAME " + LDTUTC);

        } else if (!passwordValidation(Password)) {
            helper.ErrorMsg.getError(2);
            loginActivity(User_Name + " has failed login due to incorrect PASSWORD at " + LDTUTC);


        } else if (userLogin(User_Name, Password)) {
            int userId = getUserId(User_Name);
            ObservableList<Appointment> userAppointments = AppointmentDAO.getUserAppointments(userId);
            FXMLLoader loader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            loginActivity(User_Name + " has successfully logged in on " + LocalDateTime.now());

            boolean isValid = false;
            for (Appointment appointment : userAppointments) {
                LocalDateTime startTime = appointment.getAppointmentStart();
                if ((startTime.isAfter(currentTime) || startTime.isEqual(currentTimePlus15)) &&
                        (startTime.isBefore(currentTimePlus15) || startTime.isEqual(currentTime))) {
                    System.out.println(appointment.getAppointmentId());
                    Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
                    confirmRemoval.setTitle("Alert");
                    confirmRemoval.setContentText(appointment.getAppointmentId() + " " + appointment.getAppointmentStart());
                    confirmRemoval.getButtonTypes().clear();
                    confirmRemoval.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                    confirmRemoval.showAndWait();
                    isValid = true;
                }
            }
            if (!isValid) {
                Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
                confirmRemoval.setTitle("Alert");
                confirmRemoval.setContentText("No appointments");
                confirmRemoval.getButtonTypes().clear();
                confirmRemoval.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                confirmRemoval.showAndWait();
            }
        }
    }


    /**
     * Action event for Cancel button on login screen. Displays a warning asking for confirmation
     * to exit the program (OK button) or by staying inside the program (Cancel button).
     *
     * @param actionEvent
     */
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

    /**
     * Sets the timezone label, the text-field labels, the button text and program title to change languages based on
     * user's language settings on computer.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TimeZone tz = TimeZone.getDefault();
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