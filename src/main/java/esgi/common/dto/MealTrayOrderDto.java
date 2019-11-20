package esgi.common.dto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class MealTrayOrderDto {

    private java.util.UUID uuid;
    private java.util.UUID userUuid;
    private String status;
    private java.time.LocalDate date;
    private String dateString;

    public MealTrayOrderDto(java.util.UUID uuid, java.util.UUID userUuid, String status, java.time.LocalDate date) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.status = status;
        this.date = date;
    }

    public MealTrayOrderDto (final java.util.UUID uuid, final java.util.UUID userUuid, final String status, final String dateString) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.status = status;
        this.dateString = dateString;
    }

    public String getDateString () {
        return dateString;
    }

    public void setDateString (final String dateString) {
        this.dateString = dateString;
    }

    public MealTrayOrderDto() {
    }

    public java.util.UUID getUuid() {
        return uuid;
    }

    public void setUuid(java.util.UUID uuid) {
        this.uuid = uuid;
    }

    public java.util.UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(java.util.UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.time.LocalDate getDate() {
        return date;
    }

    public void setDate(java.time.LocalDate date) {
        this.date = date;
    }
}
