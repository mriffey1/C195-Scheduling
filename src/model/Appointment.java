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
    private int appointmentTypeMonthTotals;
    private String appointmentTypeMonth;

    //  public String appointmentMonth;
    //  public int appointmentTotalappo;

    public int getAppointmentTypeMonthTotals() {
        return appointmentTypeMonthTotals;
    }

    public int appointmentTypeTotal;

    public String getAppointmentTypeMonth() {
        return appointmentTypeMonth;
    }

    public int getAppointmentTypeTotal() {
        return appointmentTypeTotal;
    }

    public Appointment(String appointmentType, int appointmentTypeTotal) {
        this.appointmentType = appointmentType;
        this.appointmentTypeTotal = appointmentTypeTotal;
    }


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

    public Appointment(String appointmentType, LocalDateTime appointmentStart) {
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
    }

    public Appointment(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Appointment(String appointmentType, long appointmentStart1) {
        this.appointmentType = appointmentType;
        this.appointmentStart1 = appointmentStart1;
    }


    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public int getAppointmentContact() {
        return appointmentContact;
    }

    public void setAppointmentContact(int appointmentContact) {
        this.appointmentContact = appointmentContact;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }


    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public int getAppointmentCustomerId() {
        return appointmentCustomerId;
    }

    public void setAppointmentCustomerId(int appointmentCustomerId) {
        this.appointmentCustomerId = appointmentCustomerId;
    }

    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }


    public static boolean overlapCheck(int customerId, LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
            ObservableList<Appointment> appointmentList = AppointmentDAO.getAppointmentList();
            LocalDateTime checkApptStart;
            LocalDateTime checkApptEnd;

            for (Appointment a : appointmentList) {
                checkApptStart = a.getAppointmentStart();
                checkApptEnd = a.getAppointmentEnd();
                if (customerId != a.getAppointmentCustomerId()) {
                    continue;
                } else if (checkApptStart.isEqual(appointmentStart) || checkApptStart.isEqual(appointmentEnd) || checkApptEnd.isEqual(appointmentStart) || checkApptEnd.isEqual(appointmentEnd)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: Appointments must not start or end at same time as existing customer appointments");
                    alert.showAndWait();
                    return true;
                } else if (appointmentStart.isAfter(checkApptStart) && (appointmentStart.isBefore(checkApptEnd))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: Appointment start must not be during existing customer appointments");
                    alert.showAndWait();
                    return true;
                } else if (appointmentEnd.isAfter(checkApptStart) && appointmentEnd.isBefore(checkApptEnd)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setContentText("ERROR: Appointment end must not be during existing customer appointments");
                    alert.showAndWait();
                    return true;
                }
            }
            return false;
        }


             //   }
           //     if (customerId == appointment.getAppointmentCustomerId() && appointmentStart.isAfter(appointment.getAppointmentStart()) || appointmentStart.isBefore(appointment.getAppointmentEnd())){
            //    System.out.println("start time overlaps with existing appointment");
            //        return false;








    public static boolean businessHours(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = appointmentStart.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = appointmentEnd.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();

        LocalDateTime busStartEST = appStartEST.withHour(8).withMinute(0);
        LocalDateTime busEndEST = appEndEST.withHour(22).withMinute(0);

        if (appStartEST.isBefore(busStartEST) || appEndEST.isAfter(busEndEST)) {
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

    public static LocalTime localStart() {
        LocalTime openingBusinessTime = LocalTime.of(8, 0);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime businessEastern = LocalDateTime.of(LocalDate.now(), openingBusinessTime);
        LocalDateTime businessLocal = businessEastern.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime busStartLocal = businessLocal.toLocalTime();

        return busStartLocal;
    }

    public static LocalTime localEnd() {
        LocalTime busEndEST = LocalTime.of(22, 0);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime busEstDT = LocalDateTime.of(LocalDate.now(), busEndEST);
        LocalDateTime busLocalDT = busEstDT.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime busEndLocal = busLocalDT.toLocalTime();


        return busEndLocal;
    }

    public static ObservableList<LocalTime> getTimes() {
        ObservableList<LocalTime> appointmentTimeList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(1, 00);
        LocalTime end = LocalTime.MIDNIGHT.minusHours(1);
        while (start.isBefore(end.plusSeconds(1))) {
            appointmentTimeList.add(start);
            start = start.plusMinutes(30);
        }
        return appointmentTimeList;
    }

    public static void backToAppointments(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Appointment.class.getResource("../view/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

