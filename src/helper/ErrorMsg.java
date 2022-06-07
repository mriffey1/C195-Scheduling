package helper;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public  class ErrorMsg implements Initializable {
    static ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    private int whichError;


    public static void getError(int whichError) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (whichError) {
            // Incorrect Username error message
            case 1:
                alert.setTitle(langBundle.getString("Error"));
                alert.setContentText(langBundle.getString("incorrectUsername"));
                alert.showAndWait();
                break;

            // Incorrect Password error message
            case 2:
                alert.setTitle(langBundle.getString("Error"));
                alert.setContentText(langBundle.getString("incorrectPassword"));
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle(langBundle.getString("Error"));
                alert.setContentText("Your Inventory must be a number equal \n" +
                        "to or between your Minimum and Maximum");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle(langBundle.getString("Error"));
                alert.setHeaderText("Error");
                alert.setContentText("Please check your minimum quantity. \n" +
                        "The minimum must be greater than zero \n" +
                        "and less than your maximum.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle(langBundle.getString("Error"));
                alert.setHeaderText("Error");
                alert.setContentText("Please fill out all fields");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle(langBundle.getString("Error"));
                alert.setContentText("Please select part.");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Product was not deleted.");
                alert.setContentText("No Product was selected.\n" +
                        "Please select a product and try again.");
                alert.showAndWait();
                break;
            case 8:
                alert.setTitle("Part Associated");
                alert.setHeaderText("Product has an associated part");
                alert.setContentText("Product cannot be deleted as there is\n" +
                        "and associated part. Please remove part and try again.");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) ;
                break;
            case 9:
                alert.setTitle("Error");
                alert.setContentText("Please select a product.");
                alert.showAndWait();
                break;
            case 10:
                alert.setTitle("Error");
                alert.setContentText("No Results found.\n" +
                        "Please check search query.");
                alert.showAndWait();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + whichError);
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
                alert.setTitle("Add Item has been canceled");
                alert.setHeaderText("Cancel");
                alert.setContentText("Are you sure you want to cancel? \nYou will return to the main screen.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) ;
                break;
            case 2:
                alert.setTitle("Delete Product");
                alert.setHeaderText("Are you sure you want to delete?");
                alert.setContentText("Press OK to delete this product. ");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) ;
                break;
            case 3:
                Alert confirmRemoval = new Alert(Alert.AlertType.WARNING);
                confirmRemoval.setTitle("Alert");
                confirmRemoval.setContentText("Remove Selected Part?");
                Optional<ButtonType> answer = confirmRemoval.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) ;
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
