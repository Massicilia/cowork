package esgi.infra.mysql;

import esgi.common.dto.TicketFullDto;
import esgi.use_case.TicketRepository;
import esgi.common.exceptions.TicketNotFoundException;
import esgi.common.exceptions.AnyTicketFoundException;
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
    public TicketFullDto getTicket (java.util.UUID uuid_ticket){
        mysqlConnection();

        String uuidCreatorString = null;
        String uuidAssignedString = null;
        UUID uuidCreator = null;
        UUID uuidAssigned = null;
        String status=null;
        LocalDate dateTicketCreation = null;
        LocalDate dateExpectedResolution = null;


        String getTicket = "SELECT UUID, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution FROM ticket WHERE UUID = '" + uuid_ticket.toString() + "' ";


        try {
            ResultSet resultset = statement.executeQuery(getTicket);
            logger.debug ("GETTICKET AFTER EXECUTE");
            if (resultset.next()) {
                status = resultset.getString("status");
                uuidCreatorString = resultset.getString ("creatorUUID");
                uuidAssignedString = resultset.getString ("assignedUUID");
                uuidCreator = UUID.fromString(uuidCreatorString);
                uuidAssigned = UUID.fromString(uuidAssignedString);


                if(resultset.getInt ("yearticketcreation") != 0 || resultset.getInt ("monthticketcreation")!= 0 || resultset.getInt ("dayticketcreation") != 0){

                    String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + resultset.getInt ("monthticketcreation") + "-" + resultset.getInt ("dayticketcreation");
                    logger.debug ("dateEndSubscriptionString" + dateTicketCreationString);
                    dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                }

                if(resultset.getInt ("yearexpectedresolution") != 0 || resultset.getInt ("monthexpectedresolution")!= 0 || resultset.getInt ("dayexpectedresolution") != 0){

                    String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + resultset.getInt ("monthexpectedresolution") + "-" + resultset.getInt ("dayexpectedresolution");
                    logger.debug ("dateEndSubscriptionString" + dateExpectedResolutionString);
                    dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);
                }






            } else {
                throw new TicketNotFoundException ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TicketFullDto ticketDto = new TicketFullDto (uuid_ticket, uuidCreator, uuidAssigned, status, dateTicketCreation, dateExpectedResolution);

        DbConnect.closeConnection(connection);
        return ticketDto;
    }

    @Override
    public List<TicketFullDto> getTickets (){
        mysqlConnection();


        List<TicketFullDto> ticketDtos = new ArrayList<> ();
        TicketFullDto ticketDto;
        String getTickets = "SELECT UUID, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
                "FROM ticket ";
        try {
            ResultSet resultset = statement.executeQuery(getTickets);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String status = resultset.getString("status");
                String uuidCreator = resultset.getString ("creatorUUID");
                String uuidAssignedString = resultset.getString ("assignedUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + resultset.getInt ("monthticketcreation") + "-" + resultset.getInt ("dayticketcreation");
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + resultset.getInt ("monthexpectedresolution") + "-" + resultset.getInt ("dayexpectedresolution");

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);

                ticketDto = new TicketFullDto (UUID.fromString(uuidString), UUID.fromString(uuidCreator), UUID.fromString(uuidAssignedString), status, dateTicketCreation, dateExpectedResolution);

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
    public List<TicketFullDto> getTicketsByCreatorUUID (java.util.UUID uuidCreator){
        mysqlConnection();
        List<TicketFullDto> ticketDtos = new ArrayList<> ();
        TicketFullDto ticketDto;
        String getTicketsByCreatorUUID = "SELECT UUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
        "FROM ticket WHERE creatorUUID = " + "'" + uuidCreator.toString() + "'";
        try {
            ResultSet resultset = statement.executeQuery(getTicketsByCreatorUUID);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String status = resultset.getString("status");
                String uuidAssignedString = resultset.getString ("assignedUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + resultset.getInt ("monthticketcreation") + "-" + resultset.getInt ("dayticketcreation");
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + resultset.getInt ("monthexpectedresolution") + "-" + resultset.getInt ("dayexpectedresolution");

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);

                ticketDto = new TicketFullDto (UUID.fromString(uuidString), uuidCreator, UUID.fromString(uuidAssignedString), status, dateTicketCreation, dateExpectedResolution);
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
    public List<TicketFullDto> getTicketsByAssigneeUUID (java.util.UUID uuidAssignee){
        mysqlConnection();
        List<TicketFullDto> ticketDtos = new ArrayList<> ();
        TicketFullDto ticketDto;
        String getTicketsByAssigneeUUID = "SELECT UUID, creatorUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution " +
                "FROM ticket WHERE creatorUUID = " + "'" + uuidAssignee.toString() + "'";
        try {
            ResultSet resultset = statement.executeQuery(getTicketsByAssigneeUUID);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String status = resultset.getString("status");
                String uuidCreatorString = resultset.getString ("creatorUUID");
                String dateTicketCreationString = resultset.getInt ("yearticketcreation") + "-" + resultset.getInt ("monthticketcreation") + "-" + resultset.getInt ("dayticketcreation");
                String dateExpectedResolutionString = resultset.getInt ("yearexpectedresolution") + "-" + resultset.getInt ("monthexpectedresolution") + "-" + resultset.getInt ("dayexpectedresolution");

                LocalDate dateTicketCreation = LocalDate.parse(dateTicketCreationString);
                LocalDate dateExpectedResolution = LocalDate.parse(dateExpectedResolutionString);

                ticketDto = new TicketFullDto (UUID.fromString(uuidString), UUID.fromString(uuidCreatorString), uuidAssignee, status, dateTicketCreation, dateExpectedResolution);
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
    public void insertTicket(TicketFullDto ticketDto){

        mysqlConnection();


        String formatDateTicketCreation = ticketDto.getDateTicketCreation ().toString ();
        String formatDateExpectedResolution = ticketDto.getDateExpectedResolution ().toString ();

        logger.debug ("formatDateTicketCreation " + formatDateTicketCreation);
        logger.debug ("formatDateExpectedResolution " + formatDateExpectedResolution);
        logger.debug ("DAY " + formatDateTicketCreation.substring (8, 10));
        logger.debug ("MONTH " + formatDateTicketCreation.substring (5, 7));
        logger.debug ("YEAR " + formatDateTicketCreation.substring (0, 4));

        String insertTicket = "INSERT INTO ticket (UUID, creatorUUID, assignedUUID, status, dayticketcreation, monthticketcreation, yearticketcreation, dayexpectedresolution, monthexpectedresolution, yearexpectedresolution)" +
                " VALUES ( '" + ticketDto.getUuidTicket ().toString() + "', '" + ticketDto.getNameCreator ().toString() + "', '" + ticketDto.getNameAssignee ().toString() + "', '" +
                ticketDto.getStatus() + "', '" +
                Integer.parseInt(formatDateTicketCreation.substring (8, 10)) + "', '" +
                Integer.parseInt(formatDateTicketCreation.substring (5, 7)) + "', '" +
                Integer.parseInt(formatDateTicketCreation.substring (0, 4)) + "', '" +
                Integer.parseInt(formatDateExpectedResolution.substring (8, 10)) + "', '" +
                Integer.parseInt(formatDateExpectedResolution.substring (5, 7)) + "', '" +
                Integer.parseInt(formatDateExpectedResolution.substring (0, 4)) + "')";


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
