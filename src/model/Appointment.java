package model;

import java.sql.Timestamp;

public class Appointment {

    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private int appointmentContact;
    private String appointmentType;
    private Timestamp appointmentStart;
    private Timestamp appointmentEnd;
    private int appointmentCustomerId;
    private int appointmentUserId;
    private String appointmentLocation;


    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, int appointmentContact,
                       String appointmentType, Timestamp appointmentStart, Timestamp appointmentEnd, int appointmentCustomerId, int appointmentUserId, String appointmentLocation) {
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

    public Timestamp getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(Timestamp appointmentStart) {
        this.appointmentStart = appointmentStart;
    }


    public Timestamp getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Timestamp appointmentEnd) {
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
}
