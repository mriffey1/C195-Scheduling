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
import java.time.*;
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


    public static boolean businessHours(LocalDateTime appointmentStart, LocalDateTime appointmentEnd) {
        ZoneId localZone = ZoneId.systemDefault();
        ZoneId estZone = ZoneId.of("America/New_York");

        LocalDateTime appStartEST = appointmentStart.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        LocalDateTime appEndEST = appointmentEnd.atZone(localZone).withZoneSameInstant(estZone).toLocalDateTime();
        //System.out.println(appStartEST);
        //System.out.println(appEndEST);
        LocalDateTime busStartEST = appStartEST.withHour(8).withMinute(0);
        LocalDateTime busEndEST = appEndEST.withHour(22).withMinute(0);
        //System.out.println(busStartEST);
        //System.out.println(busEndEST);

        if(appStartEST.isBefore(busStartEST) || appEndEST.isAfter(busEndEST)){
            return true;
        }
        else{
            return false;
        }
//        ZonedDateTime startZonedDateTime = ZonedDateTime.of(startDateTime,ZoneId.systemDefault());
//        ZonedDateTime endZonedDateTime = ZonedDateTime.of(endDateTime, ZoneId.systemDefault());
//        ZonedDateTime startBusinessHours = ZonedDateTime.of(appointmentDate, LocalTime.of(8,0),
//                ZoneId.of("America/New_York"));
//        ZonedDateTime endBusinessHours = ZonedDateTime.of(appointmentDate, LocalTime.of(22, 0),
//                ZoneId.of("America/New_York"));
//
//        //All possible conflicts for the appointment
//        return !(startZonedDateTime.isBefore(startBusinessHours) | startZonedDateTime.isAfter(endBusinessHours) |
//                endZonedDateTime.isBefore(startBusinessHours) | endZonedDateTime.isAfter(endBusinessHours) |
//                startZonedDateTime.isAfter(endZonedDateTime));
    }
//        ZonedDateTime systemZone = isApptValid.atZone(ZoneId.systemDefault());
//        systemZone = systemZone.withZoneSameInstant(ZoneId.of("US/Eastern"));
//        isApptValid = systemZone.toLocalDateTime();
//        LocalTime openingBusinessHours = LocalTime.of(8, 0);
//        LocalTime closingBusinessHours = LocalTime.of(8, 0);
//        if (isApptValid.toLocalTime().isBefore(openingBusinessHours) || isApptValid.toLocalTime().isAfter(closingBusinessHours) ||
//                isApptValid.toLocalTime().isBefore(closingBusinessHours) || isApptValid.toLocalTime().isAfter(closingBusinessHours)) {
//            return (isApptValid.toLocalTime().isBefore(openingBusinessHours) || isApptValid.toLocalTime().isAfter(closingBusinessHours) ||
//                    isApptValid.toLocalTime().isBefore(closingBusinessHours) || isApptValid.toLocalTime().isAfter(closingBusinessHours));
//        } else {
//
//            return (isApptValid.toLocalTime().isAfter(openingBusinessHours) || isApptValid.toLocalTime().equals(openingBusinessHours) && isApptValid.toLocalTime().isBefore(closingBusinessHours));
//        }

public static LocalTime localStart(){
    LocalTime busStartEST = LocalTime.of(8,0);
    ZoneId estZone = ZoneId.of("America/New_York");
    ZoneId localZone = ZoneId.systemDefault();

    LocalDateTime busEstDT = LocalDateTime.of(LocalDate.now(), busStartEST);
    LocalDateTime busLocalDT = busEstDT.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

    LocalTime busStartLocal = busLocalDT.toLocalTime();

    //System.out.println(busStartEST);
    //System.out.println(busStartLocal);
    return busStartLocal;

}

    public static LocalTime getLocalBusinessEnd(){
        LocalTime busEndEST = LocalTime.of(22,0);
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();

        LocalDateTime busEstDT = LocalDateTime.of(LocalDate.now(), busEndEST);
        LocalDateTime busLocalDT = busEstDT.atZone(estZone).withZoneSameInstant(localZone).toLocalDateTime();

        LocalTime busEndLocal = busLocalDT.toLocalTime();

        //System.out.println(busEndEST);
        //System.out.println(busEndLocal);
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

