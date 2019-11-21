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
    public RoomDto getAvailableRoom(String type, String space, LocalDate dateStart, LocalDate dateEnd) {
        mysqlConnection();
        UUID uuid = null;
        String getRoom = "SELECT UUID FROM room INNER JOIN booking ON booking.type = type and booking.space = space and (booking.dateStart > dateEnd OR booking.dateEnd < dateStart)" ;

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getRoom);
            if (resultset.next()) {
                String uuidString = resultset.getString("UUID");
                uuid = java.util.UUID.fromString(uuidString);

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

    public void saveNewBooking(RoomDto roomDto, UUID uuidUser, LocalDate dateStart, LocalDate dateEnd) {
        mysqlConnection();



        String addBooking = "INSERT INTO booking ( uuidRoom, uuiduser, type, space dateStart, dateEnd)" +
                " VALUES ( '" + roomDto.getUuid().toString() + "', '" +
                uuidUser.toString() + "', '" +
                roomDto.getType() + "', '" +
                roomDto.getSpace() + "', '" +
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
