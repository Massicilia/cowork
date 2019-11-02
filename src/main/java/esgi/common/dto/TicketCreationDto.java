package esgi.common.dto;

public class TicketCreationDto {

    private String nameCreator;
    private String surnameCreator;
    private String nameAssignee;
    private String surnameAssignee;
    private String status;
    private java.time.LocalDate dateTicketCreation;
    private java.time.LocalDate dateExpectedResolution;

    public TicketCreationDto () {
    }

    public TicketCreationDto (final String nameCreator, final String surnameCreator, final String nameAssignee, final String surnameAssignee, final String status, final java.time.LocalDate dateTicketCreation, final java.time.LocalDate dateExpectedResolution) {
        this.nameCreator = nameCreator;
        this.surnameCreator = surnameCreator;
        this.nameAssignee = nameAssignee;
        this.surnameAssignee = surnameAssignee;
        this.status = status;
        this.dateTicketCreation = dateTicketCreation;
        this.dateExpectedResolution = dateExpectedResolution;
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
