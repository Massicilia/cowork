package esgi.common.dto;

public class TicketDto {


    private java.util.UUID uuidTicket;
    private String status;

    public TicketDto (final java.util.UUID uuidTicket, final String status) {
        this.uuidTicket = uuidTicket;
        this.status = status;
    }

    public TicketDto () {
    }

    public java.util.UUID getUuidTicket () {
        return uuidTicket;
    }

    public void setUuidTicket (final java.util.UUID uuidTicket) {
        this.uuidTicket = uuidTicket;
    }

    public String getStatus () {
        return status;
    }

    public void setStatus (final String status) {
        this.status = status;
    }
}
