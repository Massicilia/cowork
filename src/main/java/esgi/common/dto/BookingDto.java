package esgi.common.dto;

import java.time.LocalDate;
import java.util.UUID;

public class BookingDto {


    private UUID uuidRoom;
    private UUID uuidUser;
    private String type;
    private String space;
    private LocalDate dateStart;
    private LocalDate dateEnd;


    public BookingDto (final java.util.UUID uuidRoom, final java.util.UUID uuidUser, final String type, final String space, final java.time.LocalDate dateStart, final java.time.LocalDate dateEnd) {
        this.uuidRoom = uuidRoom;
        this.uuidUser = uuidUser;
        this.type = type;
        this.space = space;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public BookingDto() {
    }

    public String getSpace () {
        return space;
    }

    public void setSpace (final String space) {
        this.space = space;
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