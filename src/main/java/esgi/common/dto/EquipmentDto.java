package esgi.common.dto;

import java.util.UUID;

public class EquipmentDto {

    private java.util.UUID uuidEquipment;
    private String type;
    private int available;
    private java.util.Date dateAvailability;
    private java.util.UUID uuidLoanrequester;
    private String statut;

    public EquipmentDto(java.util.UUID uuidEquipment, String type, int available,
     java.util.Date dateAvailability, java.util.UUID uuidLoanrequester, String statut){
        this.uuidEquipment = uuidEquipment;
        this.type = type;
        this.available = available;
        this.dateAvailability = dateAvailability;
        this.uuidLoanrequester = uuidLoanrequester;
        this.statut = statut;
    }

    public java.util.UUID getUuidEquipment() {
        return uuidEquipment;
    }

    public String getType() {
        return type;
    }

}
