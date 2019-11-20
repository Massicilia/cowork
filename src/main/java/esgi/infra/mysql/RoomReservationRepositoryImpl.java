package esgi.infra.mysql;

import esgi.common.dto.LoanDto;
import esgi.common.exceptions.AnyLoanFoundException;
import esgi.use_case.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomReservationRepositoryImpl implements RoomReservationRepository {
    public Statement statement = null;
    Connection connection;

	Logger logger = LoggerFactory.getLogger(RoomReservationRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RoomDto> getAvailableRooms(LocalDate date) {
        mysqlConnection();
        List<RoomDto> RoomDtos = new ArrayList<>();
        RoomDto RoomDto;
        String getLoans = "SELECT UUID, type FROM room LEFT JOIN roomreservation ON roomreservation.roomUUID = room.UUID WHERE (datereservation < startDate and datereservation < startDate) " ;


        SELECT items.id, items.name
        FROM items
        LEFT JOIN types ON items.type = types.id
        LEFT JOIN reservations ON reservations.item_id = items.id
        WHERE (((reservations.endDate NOT between $start AND $end) and
                (reservations.startDate NOT between $start AND $end)) or
                (reservations.id IS NULL)) and
                (types.id = $laptop_type_id)
        try {
            ResultSet resultset = statement.executeQuery(getLoans);
            while (resultset.next()) {

                String uuiduser = resultset.getString("uuiduser");
                String uuidequipment = resultset.getString("uuidequipment");
                logger.debug ("LOANREPOSITORYIMPL GETLOANS AFTER GETTING DB DATAS");
	            LocalDate dateLoanBegin = LocalDate.of(resultset.getInt ("yearLoanBegin"), resultset.getInt ("monthLoanBegin"), (resultset.getInt ("dayLoanBegin")));
	            LocalDate dateLoanEnd = LocalDate.of(resultset.getInt ("yearLoanEnd"), resultset.getInt ("monthLoanEnd"), (resultset.getInt ("dayLoanEnd")));

	            logger.debug ("LOANREPOSITORYIMPL GETLOANS AFTER DATES PARSING");
                loanDto = new LoanDto (UUID.fromString(uuiduser),
                        UUID.fromString(uuidequipment), dateLoanBegin, dateLoanEnd);
                loanDtos.add(loanDto);
                if (resultset == null) {
                    throw new AnyLoanFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return loanDtos;
    }

}
