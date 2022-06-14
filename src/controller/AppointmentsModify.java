package controller;

import DAO.ContactDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Contact;

public class AppointmentsModify {

    public TextField appointmentIDTextField;
    public TextField appointmentTitleTextField;
    public TextField appointmentDescriptionTextField;
    public TextField appointmentLocationTextField;
    public TextField appointmentTypeTextField;
    public ComboBox contactCombo;
    public Button saveButton;
    public Button cancelButton;
    public DatePicker datePicker;

    public void getAppointmentInfo(Appointment appointment) {
        appointmentIDTextField.setText(Integer.toString(appointment.getAppointmentId()));
        appointmentTitleTextField.setText(appointment.getAppointmentTitle());
        appointmentDescriptionTextField.setText(appointment.getAppointmentDescription());
        appointmentLocationTextField.setText(appointment.getAppointmentLocation());
        appointmentTypeTextField.setText(appointment.getAppointmentType());
        Contact s = ContactDAO.returnContactList(appointment.getAppointmentContact());
        contactCombo.setValue(s);
    }

    public void actionSaveButton(ActionEvent actionEvent) {
    }

    public void actionCancelButton(ActionEvent actionEvent) {
    }

    public void actionDatePicker(ActionEvent actionEvent) {
    }
}
