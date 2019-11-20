package esgi.common.dto;

import java.time.LocalDate;

public class TicketCreationDto {

    private String title;
    private String description;
    private String nameCreator;
    private String surnameCreator;
    private String nameAssignee;
    private String surnameAssignee;
    private String status;
    private java.time.LocalDate dateTicketCreation;
    private java.time.LocalDate dateExpectedResolution;

    public TicketCreationDto () {
    }

    public TicketCreationDto(String title, String description, String nameCreator, String surnameCreator, String nameAssignee, String surnameAssignee, String status, LocalDate dateTicketCreation, LocalDate dateExpectedResolution) {
        this.title = title;
        this.description = description;
        this.nameCreator = nameCreator;
        this.surnameCreator = surnameCreator;
        this.nameAssignee = nameAssignee;
        this.surnameAssignee = surnameAssignee;
        this.status = status;
        this.dateTicketCreation = dateTicketCreation;
        this.dateExpectedResolution = dateExpectedResolution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameCreator () {
        return nameCreator;
    }

    public void setNameCreator (final String nameCreator) {
        this.nameCreator = nameCreator;
    }

    public String getSurnameCreator () {
        return surnameCreator;
    }

    public void setSurnameCreator (final String surnameCreator) {
        this.surnameCreator = surnameCreator;
    }

    public String getNameAssignee () {
        return nameAssignee;
    }

    public void setNameAssignee (final String nameAssignee) {
        this.nameAssignee = nameAssignee;
    }

    public String getSurnameAssignee () {
        return surnameAssignee;
    }

    public void setSurnameAssignee (final String surnameAssignee) {
        this.surnameAssignee = surnameAssignee;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (final String status) {
        this.status = status;
    }

    public java.time.LocalDate getDateTicketCreation () {
        return dateTicketCreation;
    }

    public void setDateTicketCreation (final java.time.LocalDate dateTicketCreation) {
        this.dateTicketCreation = dateTicketCreation;
    }

    public java.time.LocalDate getDateExpectedResolution () {
        return dateExpectedResolution;
    }

    public void setDateExpectedResolution (final java.time.LocalDate dateExpectedResolution) {
        this.dateExpectedResolution = dateExpectedResolution;
    }
}
