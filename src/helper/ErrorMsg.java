package helper;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Error Messages class
 *
 * @author Megan Riffey
 */

public class ErrorMsg implements Initializable {
    static ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");

    /**
     * Alerts with alert type error
     *
     * @param whichError associated case number
     */
    public static void getError(int whichError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (whichError) {

            // Incorrect Username error message alert
            case 1:
                alert.setTitle(langBundle.getString("ErrorUsername"));
                alert.setContentText(langBundle.getString("incorrectUsername"));
                alert.showAndWait();
                break;

            // Incorrect Password error message alert
            case 2:
                alert.setTitle(langBundle.getString("ErrorPassword"));
                alert.setContentText(langBundle.getString("incorrectPassword"));
                alert.showAndWait();
                break;

            // Incorrect Username and Password alert
            case 3:
                alert.setTitle(langBundle.getString("ErrorUserPass"));
                alert.setContentText(langBundle.getString("incorrectEverything"));
                alert.showAndWait();
                break;

            // Deletion of customer confirmation alert
            case 4:
                alert.setTitle("Please confirm deletion");
                alert.setContentText("Please confirm you would like to delete this customer.");
                alert.showAndWait();
                break;

            // Username is blank error message alert
            case 5:
                alert.setTitle(langBundle.getString("ErrorBlankUserName"));
                alert.setContentText(langBundle.getString("blankUserName"));
                alert.showAndWait();
                break;

            // Password is blank error message alert
            case 6:
                alert.setTitle(langBundle.getString("ErrorBlankPassWord"));
                alert.setContentText(langBundle.getString("blankPassWord"));
                alert.showAndWait();
                break;

            // No customer is selected alert
            case 7:
                alert.setTitle("No Selection");
                alert.setContentText("Please select a customer to continue.");
                alert.showAndWait();
                break;

            // Title text-field is blank alert
            case 8:
                alert.setTitle("Title is blank");
                alert.setContentText("Please enter a Title.");
                alert.showAndWait();
                break;

            // Description text-field is blank alert
            case 9:
                alert.setTitle("Description is blank");
                alert.setContentText("Please enter a description.");
                alert.showAndWait();
                break;

            // Type text-field is blank alert
            case 10:
                alert.setTitle("Type is blank");
                alert.setContentText("Please enter a Type.");
                alert.showAndWait();
                break;

            // Location text-field is blank alert
            case 11:
                alert.setTitle("Location is blank");
                alert.setContentText("Please enter a Location.");
                alert.showAndWait();
                break;

            // No appointment was selected alert
            case 12:
                alert.setTitle("No Appointment Selected");
                alert.setContentText("No appointment was selected.");
                alert.showAndWait();
                break;

            // Overlapping appointments alert
            case 13:
                alert.setTitle("Overlapping appointment");
                alert.setContentText("");
                alert.showAndWait();
                break;

            // Customer Name is blank alert
            case 14:
                alert.setTitle("Customer Name is blank");
                alert.setContentText("The Customer Name field is blank.\nPlease enter a valid name.");
                alert.showAndWait();
                break;

            // Customer address is blank alert
            case 15:
                alert.setTitle("Customer Address is blank");
                alert.setContentText("The Customer Address field is blank.\nPlease enter a valid address.");
                alert.showAndWait();
                break;

            // Customer Postal code is blank alert
            case 16:
                alert.setTitle("Customer Postal Code is blank");
                alert.setContentText("The Customer postal code field is blank.\nPlease enter a valid postal code.");
                alert.showAndWait();
                break;

            // Division/Country Field is blank alert
            case 17:
                alert.setTitle("Division/Country Field");
                alert.setContentText("Please check the division and country field.");
                alert.showAndWait();
                break;

            // Start date picker is empty alert
            case 18:
                alert.setTitle("Please select a valid start date");
                alert.setContentText("The start date field is blank. Please choose a date.");
                alert.showAndWait();
                break;

            // Start time combo is empty alert
            case 19:
                alert.setTitle("Please select a valid start time");
                alert.setContentText("The start time is blank. Please choose a start time.");
                alert.showAndWait();
                break;

            // End date picker is empty alert
            case 20:
                alert.setTitle("Please select a valid end date");
                alert.setContentText("The end date field is blank. Please choose a date.");
                alert.showAndWait();
                break;

            // End time combo is empty alert
            case 21:
                alert.setTitle("Please select a valid end time");
                alert.setContentText("The end time is blank. Please choose an end time.");
                alert.showAndWait();
                break;

            // Customer combo is empty alert
            case 22:
                alert.setTitle("Please select a valid customer");
                alert.setContentText("Please select a valid customer.");
                alert.showAndWait();
                break;

            // User combo is empty alert
            case 23:
                alert.setTitle("Please select a valid user");
                alert.setContentText("Please select a valid user.");
                alert.showAndWait();
                break;

            // Contact combo is empty alert
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

            // No Appointments in the next 15 minutes alert
            case 1:
                alert.setTitle(langBundle.getString("Alert"));
                alert.setContentText(langBundle.getString("Noappointmentswithinthenext15minutes."));
                alert.showAndWait();
                break;

            // Customer has successfully been removed alert
            case 2:
                alert.setTitle("Customer has been removed");
                alert.setHeaderText("Success");
                alert.setContentText("Customer has been successfully removed. ");
                alert.showAndWait();
                break;

            // Customer has been successfully added alert
            case 3:
                alert.setTitle("Customer has been successfully added");
                alert.setHeaderText("Success");
                alert.setContentText("Customer has been successfully added. ");
                alert.showAndWait();
                break;

            // Appointment has been modified alert
            case 4:
                alert.setTitle("Appointment has been successfully modified");
                alert.setHeaderText("Success");
                alert.setContentText("Appointment has been successfully modified. ");
                alert.showAndWait();
                break;

            // Appointment successfully added alert
            case 5:
                alert.setTitle("Appointment has been successfully added");
                alert.setHeaderText("Success");
                alert.setContentText("Appointment has been successfully added. ");
                alert.showAndWait();
                break;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
