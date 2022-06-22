package model;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
        for (Appointment appointment : appointmentList) {
            if (customerId == appointment.getAppointmentCustomerId()) {
                if ((appointmentStart.isAfter(appointment.getAppointmentStart()) || appointmentStart.isEqual(appointment.getAppointmentStart())) && (appointmentEnd.isBefore(appointment.getAppointmentEnd()) ||
                        appointmentEnd.isEqual(appointment.getAppointmentEnd()))) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean businessHours(LocalDateTime isApptValid) {
        ZonedDateTime systemZone = isApptValid.atZone(ZoneId.systemDefault());
        systemZone = systemZone.withZoneSameInstant(ZoneId.of("US/Eastern"));
        isApptValid = systemZone.toLocalDateTime();
        LocalTime openingBusinessHours = LocalTime.of(8, 0);
        LocalTime closingBusinessHours = LocalTime.of(8, 0);
        if (((isApptValid.toLocalTime().isAfter(openingBusinessHours) || isApptValid.toLocalTime().equals(openingBusinessHours)) &&
                isApptValid.toLocalTime().isBefore(closingBusinessHours))) {
            System.out.println("good");
            return true;
        } else if (isApptValid.toLocalTime().isBefore(openingBusinessHours) && isApptValid.toLocalTime().isAfter(closingBusinessHours)){
            System.out.println("outside of business hours");
            return false;
        }
        return false;
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

