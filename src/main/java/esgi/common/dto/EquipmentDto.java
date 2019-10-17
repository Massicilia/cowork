package esgi.common.dto;

import java.util.UUID;

public class EquipmentDto {

    private java.util.UUID uuidEquipment;
    private String type;
    private boolean available;

    public EquipmentDto(java.util.UUID uuidClient, String type, boolean available){
        this.uuidEquipment = uuidEquipment;
        this.type = type;
        this.available = available;
    }

    public java.util.UUID getUuidEquipment() {
        return uuidEquipment;
    }

    public String getType() {
        return type;
    }

}
