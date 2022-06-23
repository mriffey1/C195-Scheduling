package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    /**
     * Redirects to login screen
     *
     * @param stage setting stage
     * @throws Exception addresses unhandled exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("/view/Login.fxml"))));
        stage.setTitle("Scheduling Application");
        stage.setScene(new Scene(root));
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * Initiates connection to database and closes connection to database. Launches program.
     *
     * @param args args
     * @throws SQLException addresses unhandled exception
     */
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}