package esgi.common.dto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class MealTrayOrderDto {

    private UUID uuid;
    private UUID userUuid;
    private String status;
    private LocalDate date;

    public MealTrayOrderDto(UUID uuid, UUID userUuid, String status, LocalDate date) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.status = status;
        this.date = date;
    }

    public MealTrayOrderDto() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
