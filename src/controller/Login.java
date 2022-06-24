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
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.UserDAO.*;

/**
 * Login class
 *
 * @author Megan Riffey
 */

public class Login implements Initializable {

    //  public static Object userName;
    @FXML
    private Label labelLocation;
    @FXML
    private TextField txtFieldUserName;
    @FXML
    private PasswordField txtFieldUserPassword;
    @FXML
    private Label labelUserName;
    @FXML
    private Label labelUserPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginTitle;
    boolean loginSuccess = false;

    // Loads language bundle to convert languages based on user's system language
    ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");

    LocalDateTime currentTime = LocalDateTime.now();
    ZonedDateTime LDTConvert = currentTime.atZone(ZoneId.systemDefault());
    LocalDateTime currentTimePlus15 = LocalDateTime.now().plusMinutes(15);
    ZonedDateTime LDTUTC = LDTConvert.withZoneSameInstant(ZoneId.of("America/New_York"));


    /**
     * Sets the timezone label, the text-field labels, the button text and program title to change languages based on
     * user's language settings on computer. Currently, French and English are supported.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        String location = ZoneId.systemDefault().toString();
        labelLocation.setText(location);
        labelUserName.setText(langBundle.getString("Username"));
        labelUserPassword.setText(langBundle.getString("Password"));
        loginTitle.setText(langBundle.getString("SchedulingApplication"));
        loginButton.setText(langBundle.getString("Login"));
        cancelButton.setText(langBundle.getString("Cancel"));
    }

    /**
     * Validates user login using username and password and displaying appropriate error or success messages, then logging
     * the successful or unsuccessful login attempts in the login_activity.txt file upon clicking the "Login" button.
     *
     * @param actionEvent event for Login button to execute validation
     * @throws IOException  addresses numerous unhandled exceptions
     * @throws SQLException addresses SQL exceptions for validation
     */
    public void actionLoginButton(ActionEvent actionEvent) throws IOException, SQLException {
        String User_Name = txtFieldUserName.getText();
        String Password = txtFieldUserPassword.getText();

        if (User_Name.isEmpty() || User_Name.isBlank()) {
            helper.ErrorMsg.getError(5);
        } else if (!passwordValidation(Password) && !usernameValidation((User_Name))) {
            helper.ErrorMsg.getError(3);
            loginActivity();
            loginSuccess = false;
       //     loginActivity("User " + " has failed login due to an incorrect USERNAME and PASSWORD " + LDTUTC);
        } else if (Password.isEmpty() || Password.isBlank()) {
            helper.ErrorMsg.getError(6);
        } else if (!usernameValidation(User_Name)) {
            helper.ErrorMsg.getError(1);
            loginSuccess = false;
            loginActivity();
       //     loginActivity("User " + " has failed login due to an incorrect USERNAME " + LDTUTC);
        } else if (!passwordValidation(Password)) {
            helper.ErrorMsg.getError(2);
            loginSuccess = false;
            loginActivity();
          //  loginActivity(User_Name + " has failed login due to incorrect PASSWORD at " + LDTUTC);
        } else if (userLogin(User_Name, Password)) {
            int userId = getUserId(User_Name);
            ObservableList<Appointment> userAppointments = AppointmentDAO.getUserAppointments(userId);
            new FXMLLoader();
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Menu.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            loginSuccess = true;
            loginActivity();


            // Checking for appointments upon successful login
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
                helper.ErrorMsg.confirmation(1);
            }
        }
    }

    /**
     * Action event for Cancel button on login screen. Displays a warning asking for confirmation
     * to exit the program (OK button) or by staying inside the program (Cancel button).
     *
     * @param actionEvent action for cancel button that closes the program
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
    interface LogActivity{
        public String getFileName();
    }

    LogActivity logActivity = () -> {
        return "login_activity.txt";
    };

    /**
     * Method to record login attempts and activity to login_activity.txt
     *
     * @throws IOException addresses unhandled exception
     */
    public void loginActivity() throws IOException {
        FileWriter fwritter = new FileWriter(logActivity.getFileName(), true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ssa");
        ZoneId localZone = ZoneId.systemDefault();
        if (loginSuccess) {
            fwritter.write(txtFieldUserName.getText() + " has successfully logged in on " + formatter.format(LDTUTC));
        } else if (!loginSuccess){
            fwritter.write(txtFieldUserName.getText() + " has failed login on " + LocalDateTime.now());
        }
        fwritter.write("\n");
        fwritter.close();

//        FileWriter fwritter = new FileWriter(filename, true);
//        fwritter.write(value);
//        fwritter.write("\n");
//        fwritter.close();
    }
}