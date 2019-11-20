package esgi.common.dto;

import java.time.LocalDate;
import java.util.UUID;

public class RoomDto {


    private UUID uuid;
    private String type;
    private String space;

    public RoomDto(UUID uuid, String type, String space) {
        this.uuid = uuid;
        this.type = type;
        this.space = space;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}