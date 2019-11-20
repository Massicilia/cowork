package esgi.common.dto;

import java.time.LocalDate;
import java.util.UUID;

public class BookingDto {


    private UUID uuidRoom;
    private UUID uuidUser;
    private String type;
    private LocalDate dateStart;
    private LocalDate dateEnd;


    public BookingDto(UUID uuidRoom, UUID uuidUser, String type, LocalDate dateStart, LocalDate dateEnd) {
        this.uuidRoom = uuidRoom;
        this.uuidUser = uuidUser;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public BookingDto() {
    }

    public UUID getUuidRoom() {
        return uuidRoom;
    }

    public void setUuidRoom(UUID uuidRoom) {
        this.uuidRoom = uuidRoom;
    }

    public UUID getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(UUID uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}