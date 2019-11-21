package esgi.use_case;

public interface BookingRepository {

    esgi.common.dto.RoomDto getAvailableRoom(String type, String space, java.time.LocalDate dateStart, java.time.LocalDate dateEnd);
    void saveNewBooking(esgi.common.dto.RoomDto roomDto, java.util.UUID uuidUser, java.time.LocalDate dateStart, java.time.LocalDate dateEnd) ;
}
