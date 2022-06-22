package helper;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class ErrorMsg implements Initializable {
    static ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    private int whichError;
    Stage stage;
    Parent scene;


    public static void getError(int whichError)  {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (whichError) {

            // Incorrect Username error message
            case 1:
                alert.setTitle(langBundle.getString("ErrorUsername"));
                alert.setContentText(langBundle.getString("incorrectUsername"));
                alert.showAndWait();
                break;

            // Incorrect Password error message
            case 2:
                alert.setTitle(langBundle.getString("ErrorPassword"));
                alert.setContentText(langBundle.getString("incorrectPassword"));
                alert.showAndWait();
                break;

            // Incorrect Username and Password
            case 3:
                alert.setTitle(langBundle.getString("ErrorUserPass"));
                alert.setContentText(langBundle.getString("incorrectEverything"));
                alert.showAndWait();
                break;

            // Deletion of customer confirmation
            case 4:
                alert.setTitle("Please confirm deletion");
                alert.setContentText("Please confirm you would like to delete this customer.");
                alert.showAndWait();
                break;

            // Username is blank error message
            case 5:
                alert.setTitle(langBundle.getString("ErrorBlankUserName"));
                alert.setContentText(langBundle.getString("blankUserName"));
                alert.showAndWait();
                break;

                // Password is blank error message
            case 6:
                alert.setTitle(langBundle.getString("ErrorBlankPassWord"));
                alert.setContentText(langBundle.getString("blankPassWord"));
                alert.showAndWait();
            case 7:
                alert.setTitle("No Selection");
                alert.setContentText("Please select a customer to continue.");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Title is blank");
                alert.setContentText("Please enter a Title.");
                alert.showAndWait();
            case 9:
                alert.setTitle("Description is blank");
                alert.setContentText("Please enter a description.");
                alert.showAndWait();
            case 10:
                alert.setTitle("Type is blank");
                alert.setContentText("Please enter a Type.");
                alert.showAndWait();
            case 11:
                alert.setTitle("Location is blank");
                alert.setContentText("Please enter a Location.");
                alert.showAndWait();
            case 12:
                alert.setTitle("No Appointment Selected");
                alert.setContentText("No appointment was selected.");
                alert.showAndWait();
            default:
                throw new IllegalStateException("Unexpected value: " + whichError);
        }


    }

    public static void confirmation(int confirmation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        switch (confirmation) {
            case 1:
             //   Alert noAppointment = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Alert");
                alert.setContentText("No appointments within the next 15 minutes.");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Customer has been removed");
                alert.setHeaderText("Success");
                alert.setContentText("Customer has been successfully removed. ");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
        }
    }

    /**
     * Warning alerts list referenced throughout the controller files as
     * helper.warningList(# of error).
     *
     * @param whichWarning integer associated with case warning
     */
    public static void warningList(int whichWarning) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        switch (whichWarning) {
            case 1:
                Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
                confirmRemoval.setTitle("Alert");
                confirmRemoval.setContentText("Remove Selected Part?");
                Optional<ButtonType> answer = confirmRemoval.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK)
                    break;
            case 2:
                alert.setTitle("Delete Product");
                alert.setHeaderText("Are you sure you want to delete?");
                alert.setContentText("Press OK to delete this product. ");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK);
                break;
            case 3:
                Alert alert2 = new Alert(Alert.AlertType.WARNING, langBundle.getString("Cancel"));
                alert2.setTitle("Exit Application");
                alert2.setHeaderText(langBundle.getString("Areyousureyouwanttoexit?"));
                alert2.setContentText(langBundle.getString("PressOKtoexitorpressCanceltostay."));
                alert2.getButtonTypes().clear();
                alert2.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                alert2.showAndWait();


        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
