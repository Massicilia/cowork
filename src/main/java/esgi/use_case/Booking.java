package esgi.use_case;

import esgi.common.dto.RoomDto;
import esgi.infra.mysql.BookingRepositoryImpl;
import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.mysql.LoanRepositoryImpl;
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
    UUID uuidUser;

    public Booking(String type, String space, LocalDate dateStart, LocalDate dateEnd, UUID uuidUser) {
        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.uuidUser = uuidUser;
    }

    public void add() {

        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl();
        RoomDto roomDto = bookingRepository.getAvailableRoom(type, space, dateStart, dateEnd);
        bookingRepository.saveNewBooking(roomDto, uuidUser, dateStart, dateEnd);
    }

}
