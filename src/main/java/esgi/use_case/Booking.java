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
    LocalDate dateStart;
    LocalDate dateEnd;
    String nameUser;
    String surnameUser;

    public Booking ( final String type, final String space, final java.time.LocalDate dateStart, final java.time.LocalDate dateEnd, final String nameUser, final String surnameUser) {

        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
    }

    public void add() {

        UserRepositoryImpl userRepoImpl =  new UserRepositoryImpl ();
        UUID uuidUser = userRepoImpl.getUuidUserByNameAndSurname (nameUser,surnameUser );
        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();
        RoomDto roomDto = bookingRepository.getAvailableRoom(type, space, dateStart.toString (), dateEnd.toString ());
        bookingRepository.saveNewBooking(roomDto, uuidUser, dateStart, dateEnd);
    }

}
