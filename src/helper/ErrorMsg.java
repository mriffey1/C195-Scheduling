package helper;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ErrorMsg implements Initializable {
    static ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    //  private int whichError;
    //  Stage stage;
    //  Parent scene;

    /**
     * Alerts with alert type error
     *
     * @param whichError associated case number
     */
    public static void getError(int whichError) {
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
                break;

            // No customer is selected
            case 7:
                alert.setTitle("No Selection");
                alert.setContentText("Please select a customer to continue.");
                alert.showAndWait();
                break;

            // Title text-field is blank
            case 8:
                alert.setTitle("Title is blank");
                alert.setContentText("Please enter a Title.");
                alert.showAndWait();
                break;

            // Description text-field is blank
            case 9:
                alert.setTitle("Description is blank");
                alert.setContentText("Please enter a description.");
                alert.showAndWait();
                break;

            // Type text-field is blank
            case 10:
                alert.setTitle("Type is blank");
                alert.setContentText("Please enter a Type.");
                alert.showAndWait();
                break;

            // Location text-field is blank
            case 11:
                alert.setTitle("Location is blank");
                alert.setContentText("Please enter a Location.");
                alert.showAndWait();
                break;

            // No appointment was selected
            case 12:
                alert.setTitle("No Appointment Selected");
                alert.setContentText("No appointment was selected.");
                alert.showAndWait();
                break;

            // Overlapping appointments
            case 13:
                alert.setTitle("Overlapping appointment");
                alert.setContentText("");
                alert.showAndWait();
                break;

            case 14:
                alert.setTitle("Customer Name is blank");
                alert.setContentText("The Customer Name field is blank.\nPlease enter a valid name.");
                alert.showAndWait();
                break;

            case 15:
                alert.setTitle("Customer Address is blank");
                alert.setContentText("The Customer Address field is blank.\nPlease enter a valid address.");
                alert.showAndWait();
                break;

            case 16:
                alert.setTitle("Customer Postal Code is blank");
                alert.setContentText("The Customer postal code field is blank.\nPlease enter a valid postal code.");
                alert.showAndWait();
                break;

            case 17:
                alert.setTitle("Division/Country Field");
                alert.setContentText("Please check the division and country field.");
                alert.showAndWait();
                break;

            case 18:
                alert.setTitle("Please select a valid start date");
                alert.setContentText("The start date field is blank. Please choose a date.");
                alert.showAndWait();
                break;

            case 19:
                alert.setTitle("Please select a valid start time");
                alert.setContentText("The start time is blank. Please choose a start time.");
                alert.showAndWait();
                break;
            case 20:
                alert.setTitle("Please select a valid end date");
                alert.setContentText("The end date field is blank. Please choose a date.");
                alert.showAndWait();
                break;
            case 21:
                alert.setTitle("Please select a valid end time");
                alert.setContentText("The end time is blank. Please choose an end time.");
                alert.showAndWait();
                break;
            case 22:
                alert.setTitle("Please select a valid customer");
                alert.setContentText("Please select a valid customer.");
                alert.showAndWait();
                break;
            case 23:
                alert.setTitle("Please select a valid user");
                alert.setContentText("Please select a valid user.");
                alert.showAndWait();
                break;
            case 24:
                alert.setTitle("Please select a valid contact");
                alert.setContentText("Please select a valid contact.");
                alert.showAndWait();
                break;
            // Default that throws an error
            default:
                throw new IllegalStateException("Unexpected value: " + whichError);
        }
    }

    /**
     * Alerts with alert type: confirmation
     *
     * @param confirm associated case number
     */
    public static void confirmation(int confirm) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        switch (confirm) {

            // No Appointments in the next 15 minutes
            case 1:
                alert.setTitle("Alert");
                alert.setContentText("No appointments within the next 15 minutes.");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;

            // Customer has successfully been removed
            case 2:
                alert.setTitle("Customer has been removed");
                alert.setHeaderText("Success");
                alert.setContentText("Customer has been successfully removed. ");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Customer has been successfully added");
                alert.setHeaderText("Success");
                alert.setContentText("Customer has been successfully added. ");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Appointment has been successfully modified");
                alert.setHeaderText("Success");
                alert.setContentText("Appointment has been successfully modified. ");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Appointment has been successfully added");
                alert.setHeaderText("Success");
                alert.setContentText("Appointment has been successfully added. ");
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                alert.showAndWait();
                break;
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
                if (alert.getResult() == ButtonType.OK) ;
                break;
            case 3:
                Alert alert2 = new Alert(Alert.AlertType.WARNING, langBundle.getString("Cancel"));
                alert2.setTitle("Exit Application");
                alert2.setHeaderText(langBundle.getString("Areyousureyouwanttoexit?"));
                alert2.setContentText(langBundle.getString("PressOKtoexitorpressCanceltostay."));
                alert2.getButtonTypes().clear();
                alert2.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
                alert2.showAndWait();
                break;

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
