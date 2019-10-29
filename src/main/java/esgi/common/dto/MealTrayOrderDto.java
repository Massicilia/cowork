package esgi.common.dto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class MealTrayOrderDto {

    private UUID userUuid;
    private String status;
    private LocalDate date;

    public MealTrayOrderDto( UUID userUuid, String status, LocalDate date) {
        this.userUuid = userUuid;
        this.status = status;
        this.date = date;
    }

    public MealTrayOrderDto() {
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
