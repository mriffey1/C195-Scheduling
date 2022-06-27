package model;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Model class for Appointments
 *
 * @author Megan Riffey
 */
public class Appointment {

    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private int appointmentContact;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    public long appointmentStart1;
    private LocalDateTime appointmentEnd;
    private int appointmentCustomerId;
    private int appointmentUserId;
    private String appointmentLocation;
    private int appointmentTypeTotal;
    private String appointmentContactName;

    /**
     * Overloaded constructor for Appointments to retrieve information from the database
     *
     * @param appointmentId          appointment id
     * @param appointmentTitle       appointment title
     * @param appointmentDescription appointment description
     * @param appointmentContact     associated appointment contact Id
     * @param appointmentType        appointment type
     * @param appointmentStart       appointment start date/time
     * @param appointmentEnd         appointment end date/time
     * @param appointmentCustomerId  associated appointment customer id
     * @param appointmentUserId      associated appointment user id
     * @param appointmentLocation    appointment location
     */
    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, int appointmentContact,
                       String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentLocation) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCustomerId = appointmentCustomerId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentLocation = appointmentLocation;

    }

    /**
     * Overloaded constructor for Appointments that gets the appointment types and totals in database and displays on
     * report screen.
     *
     * @param appointmentType      appointment type
     * @param appointmentTypeTotal appointment total for each type
     */
    public Appointment(String appointmentType, int appointmentTypeTotal) {
        this.appointmentType = appointmentType;
        this.appointmentTypeTotal = appointmentTypeTotal;
    }

    /**
     * Overloaded constructor for Appointments to retrieve information from the database
     *
     * @param appointmentId          appointment id
     * @param appointmentTitle       appointment title
     * @param appointmentDescription appointment description
     * @param appointmentContact     associated appointment contact Id
     * @param appointmentContactName associated appointment contact name
     * @param appointmentType        appointment type
     * @param appointmentStart       appointment start date/time
     * @param appointmentEnd         appointment end date/time
     * @param appointmentCustomerId  associated appointment customer id
     * @param appointmentUserId      associated appointment user id
     * @param appointmentLocation    appointment location
     */
    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, int appointmentContact, String appointmentContactName, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentLocation) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentContact = appointmentContact;
        this.appointmentContactName = appointmentContactName;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCustomerId = appointmentCustomerId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Getter for appointment id
     *
     * @return appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Setter for appointment id
     *
     * @param appointmentId appointment Id
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Getter for appointment title
     *
     * @return appointmentTitle
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Setter for appointment title
     *
     * @param appointmentTitle appointment Title
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Getter for appointment description
     *
     * @return appointmentDescription
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Setter for appointment description
     *
     * @param appointmentDescription appointment description
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Getter for appointment contact Id
     *
     * @return appointmentContact
     */
    public int getAppointmentContact() {
        return appointmentContact;
    }

    /**
     * Setter for appointment contact Id
     *
     * @param appointmentContact appointment contact Id
     */
    public void setAppointmentContact(int appointmentContact) {
        this.appointmentContact = appointmentContact;
    }

    public String getAppointmentContactName() {
        return appointmentContactName;
    }

    public void setAppointmentContactName(String appointmentContactName) {
        this.appointmentContactName = appointmentContactName;
    }

    /**
     * Getter for appointment type
     *
     * @return appointmentType
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Setter for appointment type
     *
     * @param appointmentType appointment Type
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Getter for appointment start date/time
     *
     * @return appointmentStart
     */
    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    /**
     * Setter for appointment start date/time
     *
     * @param appointmentStart appointment start date/time
     */
    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    /**
     * Getter for appointment end date/time
     *
     * @return appointmentEnd
     */
    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    /**
     * Setter for appointment end date/time
     *
     * @param appointmentEnd appointment end date/time
     */
    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    /**
     * Getter for appointment customer Id
     *
     * @return appointmentCustomerId
     */
    public int getAppointmentCustomerId() {
        return appointmentCustomerId;
    }

    /**
     * Setter for appointment customer Id
     *
     * @param appointmentCustomerId customer Id
     */
    public void setAppointmentCustomerId(int appointmentCustomerId) {
        this.appointmentCustomerId = appointmentCustomerId;
    }

    /**
     * Getter for appointment user id
     *
     * @return appointmentUserId
     */
    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    /**
     * Setter for appointment user Id
     *
     * @param appointmentUserId appointmentUserId
     */
    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    /**
     * Getter for appointment location
     *
     * @return appointmentLocation
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Setter for appointment location
     *
     * @param appointmentLocation appointment location
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Getter for appointment type total
     *
     * @return appointmentTypeTotal
     */
    public int getAppointmentTypeTotal() {
        return appointmentTypeTotal;
    }

    /**
     * Setter for appointment type total
     *
     * @param appointmentTypeTotal appointment type total
     */
    public void setAppointmentTypeTotal(int appointmentTypeTotal) {
        this.appointmentTypeTotal = appointmentTypeTotal;
    }


    /**
     * Boolean method to check if an appointment for the selected contact is overlying with any existing appointments for
     * selected contact
     *
     * @param customerId       customer id
     * @param appointmentStart appointment start date/time
     * @param appointmentEnd   appointment end date/time
     * @return true/false
     */
    public static boolean overlapCheck(int customerId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();
        LocalDateTime checkApptStart;
        LocalDateTime checkApptEnd;
        for (Appointment a : appointmentList) {
            checkApptStart = a.getAppointmentStart();
            checkApptEnd = a.getAppointmentEnd();
            if (customerId != a.getAppointmentCustomerId()) {
                continue;
            } else if (checkApptStart.isEqual(appointmentStart) || checkApptEnd.isEqual(appointmentEnd)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointments must not start or end at same time as existing customer appointments");
                alert.showAndWait();
                return true;
            } else if (appointmentStart.isAfter(checkApptStart) && (appointmentStart.isBefore(checkApptEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointment start must not be during existing customer appointments");
                alert.showAndWait();
                return true;
            } else if (appointmentEnd.isAfter(checkApptStart) && appointmentEnd.isBefore(checkApptEnd)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("ERROR: Appointment end must not be during existing customer appointments");
                alert.showAndWait();
                return true;
            }
        }
        return false;
    }

    /**
     * Boolean method that establishes business hours in eastern time and converts the users local timezone to ensure
     * appointment times are within business hours.
     *
     * @param appointmentStart appointment start date/time
     * @param appointmentEnd   appointment end date/time
     * @return true/false
     */
    public static boolean businessHours(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = appointmentStart.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = appointmentEnd.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();

        LocalDateTime businessStartEST = appStartEST.withHour(8).withMinute(0);
        LocalDateTime businessEndEST = appEndEST.withHour(22).withMinute(0);

        if (appStartEST.isBefore(businessStartEST) || appEndEST.isAfter(businessEndEST)) {
            LocalTime localStart = Appointment.localStart();
            LocalTime localEnd = Appointment.localEnd();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("");
            alert1.setHeaderText("Outside of Business Hours");
            alert1.setContentText(String.format("Appointment is outside of business hours: 8:00AM to 10:00PM EST\n" +
                    "Please schedule between " + localStart.format(DateTimeFormatter.ofPattern("hh:mm")) + " - " + localEnd.format(DateTimeFormatter.ofPattern("hh:mm")) + "PM local time."));
            alert1.getDialogPane().setMinHeight(250);
            alert1.getDialogPane().setMinWidth(400);
            alert1.showAndWait();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to establish local start time from eastern and display in business hours as an alert for users to display their valid local times
     * for scheduling appointments
     *
     * @return businessStartLocal
     */
    public static LocalTime localStart() {
        LocalTime openingBusinessTime = LocalTime.of(8, 0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEastern = LocalDateTime.of(LocalDate.now(), openingBusinessTime);
        LocalDateTime businessLocal = businessEastern.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessStartLocal = businessLocal.toLocalTime();

        return businessStartLocal;
    }

    /**
     * Method to establish local end time from eastern and display in business hours as an alert for users to display their valid local times
     * for scheduling appointments
     *
     * @return businessEndLocal
     */
    public static LocalTime localEnd() {
        LocalTime closingBusinessTime = LocalTime.of(22, 0);
        ZoneId easternZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEndDT = LocalDateTime.of(LocalDate.now(), closingBusinessTime);
        LocalDateTime businessLocalDT = businessEndDT.atZone(easternZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime businessEndLocal = businessLocalDT.toLocalTime();

        return businessEndLocal;
    }

    /**
     * Method to generate and add appointment times every 30 minutes to observablelist
     *
     * @return appointmentTimeList
     */
    public static ObservableList<LocalTime> getTimes() {
        ObservableList<LocalTime> appointmentTimeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(1, 00);
        LocalTime end = LocalTime.MIDNIGHT.minusHours(1);

        while (start.isBefore(end.plusSeconds(2))) {

            appointmentTimeList.add(start);
            start = start.plusMinutes(30);

        }
        return appointmentTimeList;
    }


    /**
     * Action event to return to main appointments screen
     *
     * @param actionEvent event for returning to appointments screen
     * @throws IOException addresses unhandled exceptions
     */
    public static void backToAppointments(ActionEvent actionEvent) throws IOException {
        new FXMLLoader();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Appointment.class.getResource("../view/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}