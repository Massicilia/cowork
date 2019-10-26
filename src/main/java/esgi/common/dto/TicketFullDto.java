package esgi.common.dto;

import java.util.UUID;
import java.time.LocalDate;

public class TicketFullDto {

    private UUID uuidTicket;
    private UUID nameCreator;
    private UUID nameAssignee;
    private String status;
    private LocalDate dateTicketCreation;
    private LocalDate dateExpectedResolution;

    public TicketFullDto (final java.util.UUID uuidTicket, final java.util.UUID nameCreator, final java.util.UUID nameAssignee, final String status, final java.time.LocalDate dateTicketCreation, final java.time.LocalDate dateExpectedResolution) {
        this.uuidTicket = uuidTicket;
        this.nameCreator = nameCreator;
        this.nameAssignee = nameAssignee;
        this.status = status;
        this.dateTicketCreation = dateTicketCreation;
        this.dateExpectedResolution = dateExpectedResolution;
    }

    public TicketFullDto (final java.util.UUID nameCreator, final java.util.UUID nameAssignee, final String status, final java.time.LocalDate dateTicketCreation, final java.time.LocalDate dateExpectedResolution) {
        this.nameCreator = nameCreator;
        this.nameAssignee = nameAssignee;
        this.status = status;
        this.dateTicketCreation = dateTicketCreation;
        this.dateExpectedResolution = dateExpectedResolution;
    }

    public TicketFullDto (final java.util.UUID uuidTicket) {
        this.uuidTicket = uuidTicket;
    }

    public TicketFullDto () {
    }

    public java.util.UUID getUuidTicket () {
        return uuidTicket;
    }

    public void setUuidTicket (final java.util.UUID uuidTicket) {
        this.uuidTicket = uuidTicket;
    }

    public java.util.UUID getNameCreator () {
        return nameCreator;
    }

    public void setNameCreator (final java.util.UUID nameCreator) {
        this.nameCreator = nameCreator;
    }

    public java.util.UUID getNameAssignee () {
        return nameAssignee;
    }

    public void setNameAssignee (final java.util.UUID nameAssignee) {
        this.nameAssignee = nameAssignee;
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
