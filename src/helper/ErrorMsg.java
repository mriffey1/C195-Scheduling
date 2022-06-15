package helper;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ErrorMsg implements Initializable {
    static ResourceBundle langBundle = ResourceBundle.getBundle("language/lang");
    private int whichError;


    public static void getError(int whichError) throws IOException {
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
                alert.setTitle("ErrorBlankUserName");
                alert.setContentText("blankUserName");
                alert.showAndWait();
                break;

                // Password is blank error message
            case 6:
                alert.setTitle("ErrorBlankPassWord");
                alert.setContentText("blankPassWord");
                alert.showAndWait();
            default:
                throw new IllegalStateException("Unexpected value: " + whichError);
        }


    }

    public static void confirmation(int confirmation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        switch (confirmation) {
            case 1:
                alert.setTitle(langBundle.getString("Successfullyloggedin"));
                alert.setHeaderText(langBundle.getString("Success"));
                alert.setContentText(langBundle.getString("loggedInMessage"));
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.OK);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) ;
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

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
