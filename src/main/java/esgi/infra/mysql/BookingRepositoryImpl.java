package esgi.infra.mysql;

import esgi.use_case.BookingRepository;
import esgi.common.dto.RoomDto;
import esgi.common.dto.BookingDto;
import esgi.common.exceptions.AnyRoomFoundException;
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

public class BookingRepositoryImpl implements BookingRepository {
    public Statement statement = null;
    Connection connection;

	Logger logger = LoggerFactory.getLogger(BookingRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RoomDto getAvailableRoom(String type, String space, String dateStart, String dateEnd) {
        mysqlConnection();
        logger.debug("BOOKINGREPOIMPL GET AVAIBLABLE ROOM");
        UUID uuid = null;//WHERE DATEDIFF(mydata,'2008-11-20') >=0;
        String getRoom= "SELECT r.UUID FROM room r, booking b WHERE r.UUID = b.uuidroom AND r.type = '" + type + "' AND r.space = '" + space + "' AND (b.datestart > '" + dateEnd + "' OR b.dateend < '" + dateStart + "')";
        logger.debug("BOOKINGREPOIMPL GET AVAIBLABLE ROOM getroom : " + getRoom );
        try {
            java.sql.ResultSet resultset = statement.executeQuery(getRoom);
            if (resultset.next()) {
                String uuidString = resultset.getString("UUID");
                uuid = java.util.UUID.fromString(uuidString);
                logger.debug("BOOKINGREPOIMPL GET AVAIBLABLE ROOM after request");
                logger.debug("BOOKINGREPOIMPL GET AVAIBLABLE ROOM uuid room : " + uuidString);
            }else{
                throw new AnyRoomFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RoomDto roomDto = new RoomDto(uuid, space, type);

        DbConnect.closeConnection(connection);
        return roomDto;
    }

    public void saveNewBooking(RoomDto roomDto, UUID uuidUser, String dateStart, String dateEnd) {
        mysqlConnection();



        String addBooking = "INSERT INTO booking ( uuidRoom, uuiduser, dateStart, dateEnd)" +
                " VALUES ( '" + roomDto.getUuid().toString() + "', '" +
                uuidUser.toString() + "', '" +
                dateStart + "', '" +
                dateEnd + "')";


        try {
            statement.execute(addBooking);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
    }

}
