package esgi.use_case;

public interface BookingRepository {

    esgi.common.dto.RoomDto getAvailableRoom(String type, String space, String dateStart, String dateEnd);
    void saveNewBooking(esgi.common.dto.RoomDto roomDto, java.util.UUID uuidUser, String dateStart, String dateEnd) ;
}
