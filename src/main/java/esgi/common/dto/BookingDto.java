package esgi.common.dto;

import java.time.LocalDate;
import java.util.UUID;

public class BookingDto {


    private UUID uuidRoom;
    private UUID uuidUser;
    private java.time.LocalDateTime dateStart;
    private java.time.LocalDateTime dateEnd;

    public BookingDto (final java.util.UUID uuidRoom, final java.util.UUID uuidUser, final java.time.LocalDateTime dateStart, final java.time.LocalDateTime dateEnd) {
        this.uuidRoom = uuidRoom;
        this.uuidUser = uuidUser;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public BookingDto() {
    }

    public java.util.UUID getUuidRoom () {
        return uuidRoom;
    }

    public void setUuidRoom (final java.util.UUID uuidRoom) {
        this.uuidRoom = uuidRoom;
    }

    public java.util.UUID getUuidUser () {
        return uuidUser;
    }

    public void setUuidUser (final java.util.UUID uuidUser) {
        this.uuidUser = uuidUser;
    }

    public java.time.LocalDateTime getDateStart () {
        return dateStart;
    }

    public void setDateStart (final java.time.LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public java.time.LocalDateTime getDateEnd () {
        return dateEnd;
    }

    public void setDateEnd (final java.time.LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }
}