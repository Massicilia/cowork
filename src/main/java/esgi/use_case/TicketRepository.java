package esgi.use_case;

public interface TicketRepository {


    esgi.common.dto.TicketFullDto getTicket (java.util.UUID uuid_ticket);
    java.util.List<esgi.common.dto.TicketFullDto> getTickets ();
    java.util.List<esgi.common.dto.TicketFullDto> getTicketsByCreatorUUID (java.util.UUID uuidCreator);
    java.util.List<esgi.common.dto.TicketFullDto> getTicketsByAssigneeUUID (java.util.UUID uuidAssignee);
    void updateTicketStatus (java.util.UUID uuid_ticket, String status);
    void insertTicket(esgi.common.dto.TicketFullDto ticketDto);

}
