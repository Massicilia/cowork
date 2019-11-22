package esgi.use_case;

import esgi.common.dto.RoomDto;
import esgi.infra.mysql.BookingRepositoryImpl;
import esgi.infra.mysql.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.UUID;

public class Booking {

    Logger logger = LoggerFactory.getLogger(Booking.class);

    String type;
    String space;
    java.time.LocalDateTime dateStart;
    java.time.LocalDateTime dateEnd;
    String nameUser;
    String surnameUser;

    public Booking (final String type, final String space, final java.time.LocalDateTime dateStart, final java.time.LocalDateTime dateEnd, final String nameUser, final String surnameUser) {
        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
    }

    public void add() {
        logger.debug ("USE CASE BOOKING");
        UserRepositoryImpl userRepoImpl =  new UserRepositoryImpl ();
        UUID uuidUser = userRepoImpl.getUuidUserByNameAndSurname (nameUser,surnameUser );
        logger.debug ("USE CASE BOOKING UUID USER : " + uuidUser);
        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();
        RoomDto roomDto = bookingRepository.getAvailableRoom(type, space, dateStart.toString (), dateEnd.toString ());
        logger.debug ("USE CASE BOOKING ROOM UUID : " + roomDto.getUuid () );
        bookingRepository.saveNewBooking(roomDto, uuidUser, dateStart.toString (), dateEnd.toString ());
    }

}
