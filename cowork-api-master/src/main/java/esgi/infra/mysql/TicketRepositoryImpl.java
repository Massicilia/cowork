package esgi.infra.mysql;

import esgi.common.dto.TicketFullDto;
import esgi.use_case.TicketRepository;
import esgi.common.exceptions.TicketNotFoundException;
import esgi.common.exceptions.AnyTicketFoundException;
import  esgi.infra.DateFormat;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.time.LocalDate;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.util.ArrayList;


public class TicketRepositoryImpl implements TicketRepository {
    public Statement statement = null;
    Connection connection;
    DateFormat dateFormat = new esgi.infra.DateFormat ();
	Logger logger = LoggerFactory.getLogger(esgi.infra.mysql.TicketRepositoryImpl.class);

    void mysqlConnection() {
        connection = esgi.infra.mysql.DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public esgi.common.dto.TicketCreationDto getTicket (java.util.UUID uuid_ticket){
        mysqlConnection();

        String title = null;
        String description = null;
        String uuidCreatorString = null;
        String uuidAssignedString = null;
        UUID uuidCreator = null;
        UUID uuidAssigned = null;
        String status=null;
        LocalDate dateTicketCreation = null;
        LocalDate dateExpectedResolution = null;


        String getTicket = "SELECT title, description, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution FROM ticket WHERE UUID = '" + uuid_ticket.toString() + "' ";


        try {
            ResultSet resultset = statement.executeQuery(getTicket);

            if (resultset.next()) {
                title = resultset.getString("title");
                description = resultset.getString("description");
                status = resultset.getString("status");
                uuidCreatorString = resultset.getString ("creatorUUID");
                uuidAssignedString = resultset.getString ("assignedUUID");
                uuidCreator = UUID.fromString(uuidCreatorString);
                uuidAssigned = UUID.fromString(uuidAssignedString);


                if(resultset.getInt ("yearticketcreation") != 0 || resultset.getInt ("monthticketcreation")!= 0 || resultset.getInt ("dayticketcreation") != 0){

                    String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthticketcreation")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayticketcreation"));
                    logger.debug ("dateEndSubscriptionString" + dateTicketCreationString);
                    dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                }

                if(resultset.getInt ("yearexpectedresolution") != 0 || resultset.getInt ("monthexpectedresolution")!= 0 || resultset.getInt ("dayexpectedresolution") != 0){

                    String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthexpectedresolution")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayexpectedresolution"));
                    logger.debug ("dateEndSubscriptionString" + dateExpectedResolutionString);
                    dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);
                }

            } else {
                throw new TicketNotFoundException ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get name & surname of the users
        UserRepositoryImpl userRepository = new UserRepositoryImpl ();
        esgi.common.dto.UserFullDto creator = userRepository.getUser (uuidCreator);
        esgi.common.dto.UserFullDto assigned = userRepository.getUser (uuidAssigned);

        esgi.common.dto.TicketCreationDto ticketDto = new esgi.common.dto.TicketCreationDto (title, description, creator.getName (), creator.getSurname (), assigned.getName (), assigned.getSurname (), status, dateTicketCreation, dateExpectedResolution);
        DbConnect.closeConnection(connection);
        return ticketDto;
    }

    @Override
    public List<esgi.common.dto.TicketFullDto> getTickets (){
        mysqlConnection();


        List<esgi.common.dto.TicketFullDto> tickets = new ArrayList<> ();
        esgi.common.dto.TicketFullDto ticket;
        String getTickets = "SELECT UUID, title, description, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
                "FROM ticket ";
        try {
            ResultSet resultset = statement.executeQuery(getTickets);
            while (resultset.next()) {
                String uuidString = resultset.getString ("UUID");
                String status = resultset.getString("status");
                String title = resultset.getString("title");
                String description = resultset.getString("description");
                String uuidCreator = resultset.getString ("creatorUUID");
                String uuidAssigned = resultset.getString ("assignedUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + dateFormat.getFormatTwoChar(resultset.getInt ("monthticketcreation")) + "-" + dateFormat.getFormatTwoChar(resultset.getInt ("dayticketcreation"));
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + dateFormat.getFormatTwoChar(resultset.getInt ("monthexpectedresolution")) + "-" + dateFormat.getFormatTwoChar(resultset.getInt ("monthexpectedresolution"));

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);

                //get name & surname of the users
                UserRepositoryImpl userRepository = new UserRepositoryImpl ();
                esgi.common.dto.UserFullDto creator = userRepository.getUser (UUID.fromString(uuidCreator));
                esgi.common.dto.UserFullDto assigned = userRepository.getUser (UUID.fromString(uuidAssigned));


                ticket = new esgi.common.dto.TicketFullDto (UUID.fromString(uuidString), title, description, UUID.fromString(uuidCreator),
                        UUID.fromString(uuidAssigned), status, dateTicketCreation, dateExpectedResolution);

                tickets.add(ticket);
                if (resultset == null) {
                    throw new AnyTicketFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return tickets;
    }

    @Override
    public List<esgi.common.dto.TicketCreationDto> getTicketsByCreatorUUID (java.util.UUID uuidCreator){
        mysqlConnection();
        List<esgi.common.dto.TicketCreationDto> ticketDtos = new ArrayList<> ();
        esgi.common.dto.TicketCreationDto ticketDto;
        String getTicketsByCreatorUUID = "SELECT title, description, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
        "FROM ticket WHERE creatorUUID = " + "'" + uuidCreator.toString() + "'";
        try {
            ResultSet resultset = statement.executeQuery(getTicketsByCreatorUUID);
            while (resultset.next()) {

                String title = resultset.getString("title");
                String description = resultset.getString("description");
                String status = resultset.getString("status");
                String uuidAssignedString = resultset.getString ("assignedUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthticketcreation")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayticketcreation"));
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthexpectedresolution")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayexpectedresolution"));

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);


                //get name & surname of the users
                UserRepositoryImpl userRepository = new UserRepositoryImpl ();
                esgi.common.dto.UserFullDto creator = userRepository.getUser (uuidCreator);
                esgi.common.dto.UserFullDto assigned = userRepository.getUser (UUID.fromString(uuidAssignedString));

                ticketDto = new esgi.common.dto.TicketCreationDto (title, description, creator.getName (), creator.getSurname (), assigned.getName (), assigned.getSurname (), status, dateTicketCreation, dateExpectedResolution);
                ticketDtos.add(ticketDto);
                if (resultset == null) {
                    throw new AnyTicketFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return ticketDtos;
    }

    @Override
    public List<esgi.common.dto.TicketCreationDto> getTicketsByAssigneeUUID (java.util.UUID uuidAssignee){
        mysqlConnection();
        List<esgi.common.dto.TicketCreationDto> ticketDtos = new ArrayList<> ();
        esgi.common.dto.TicketCreationDto ticketDto;
        String getTicketsByAssigneeUUID = "SELECT title, description, creatorUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
                "FROM ticket WHERE assignedUUID = " + "'" + uuidAssignee.toString() + "'";

        try {
            ResultSet resultset = statement.executeQuery(getTicketsByAssigneeUUID);
            while (resultset.next()) {

                String title = resultset.getString("title");
                String description = resultset.getString("description");
                String status = resultset.getString("status");
                String uuidCreatorString = resultset.getString ("creatorUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthticketcreation")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayticketcreation"));
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthexpectedresolution")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayexpectedresolution"));

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);

                //get name & surname of the users
                UserRepositoryImpl userRepository = new UserRepositoryImpl ();
                esgi.common.dto.UserFullDto creator = userRepository.getUser (uuidAssignee);
                esgi.common.dto.UserFullDto assigned = userRepository.getUser (UUID.fromString(uuidCreatorString));

                ticketDto = new esgi.common.dto.TicketCreationDto (title, description, creator.getName (), creator.getSurname (), assigned.getName (), assigned.getSurname (), status, dateTicketCreation, dateExpectedResolution);

                ticketDtos.add(ticketDto);
                if (resultset == null) {
                    throw new AnyTicketFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return ticketDtos;
    }

    @Override
    public void updateTicketStatus (java.util.UUID uuid_ticket, String status){
        mysqlConnection();


        String setNewStatusToTicket = "UPDATE ticket SET status ='" + status + "' WHERE UUID = '" + uuid_ticket.toString () + "'";

        try {
            statement.executeUpdate (setNewStatusToTicket);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertTicket(esgi.common.dto.TicketCreationDto ticketDto, TicketFullDto ticket,
                             java.util.UUID uuidCreator, java.util.UUID uuidAssigned){

        mysqlConnection();

        ticket.setTitle(ticketDto.getTitle());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setUuidCreator (uuidCreator);
        ticket.setUuidAssignee (uuidAssigned);
        ticket.setStatus (ticketDto.getStatus ());
        ticket.setDateTicketCreation (ticketDto.getDateTicketCreation ());
        ticket.setDateExpectedResolution (ticketDto.getDateExpectedResolution ());
        String formatDateTicketCreation = ticket.getDateTicketCreation ().toString ();
        String formatDateExpectedResolution = ticket.getDateExpectedResolution ().toString ();

        String insertTicket = "INSERT INTO ticket (UUID, title, description, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution) VALUES ( '" + ticket.getUuidTicket ().toString() + "', '" + ticket.getTitle() + "', '"  + ticket.getDescription() + "', '"  + ticket.getUuidCreator ().toString() + "', '" + ticket.getUuidAssignee ().toString() + "', '" + ticket.getStatus() + "', '" + Integer.parseInt(formatDateTicketCreation.substring (8, 10)) + "', '" + Integer.parseInt(formatDateTicketCreation.substring (5, 7)) + "', '" + Integer.parseInt(formatDateTicketCreation.substring (0, 4)) + "', '" + Integer.parseInt(formatDateExpectedResolution.substring (8, 10)) + "', '" + Integer.parseInt(formatDateExpectedResolution.substring (5, 7)) + "', '" + Integer.parseInt(formatDateExpectedResolution.substring (0, 4)) + "')";

        try {
            statement.execute(insertTicket);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);


    }

    public TicketFullDto generateUUID(TicketFullDto ticket) {
        boolean uuidExist = true;
        UUID uuidTicket = UUID.randomUUID();
        while (uuidExist) {
            uuidTicket = UUID.randomUUID();
            List<TicketFullDto> ticketDtos = this.getTickets();
            uuidExist = ticketDtos.stream()
                    .map(TicketFullDto::getUuidTicket)
                    .anyMatch(uuidTicket::equals);
        }
        ticket.setUuidTicket(uuidTicket);
        return ticket;
    }

}
