package esgi.use_case;

public interface TicketRepository {


    esgi.common.dto.TicketCreationDto getTicket (java.util.UUID uuid_ticket);
    java.util.List<esgi.common.dto.TicketFullDto> getTickets ();
    java.util.List<esgi.common.dto.TicketCreationDto> getTicketsByCreatorUUID (java.util.UUID uuidCreator);
    java.util.List<esgi.common.dto.TicketCreationDto> getTicketsByAssigneeUUID (java.util.UUID uuidAssignee);
    void updateTicketStatus (java.util.UUID uuid_ticket, String status);
    void insertTicket(esgi.common.dto.TicketCreationDto ticketDto, esgi.common.dto.TicketFullDto ticket, java.util.UUID uuidCreator, java.util.UUID uuidAssigned);

}
