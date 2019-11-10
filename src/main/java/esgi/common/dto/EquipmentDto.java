package esgi.common.dto;

import java.util.UUID;

public class EquipmentDto {

    private java.util.UUID uuidEquipment;
    private String type;
    private int available;
    private java.time.LocalDate dateAvailability;
    private java.util.UUID uuidLoanrequester;
    private String statut;
    private java.util.UUID uuidSpace;

    public EquipmentDto(java.util.UUID uuidEquipment, String type, int available,
                        java.time.LocalDate dateAvailability, java.util.UUID uuidLoanrequester, String statut, java.util.UUID uuidSpace){
        this.uuidEquipment = uuidEquipment;
        this.type = type;
        this.available = available;
        this.dateAvailability = dateAvailability;
        this.uuidLoanrequester = uuidLoanrequester;
        this.statut = statut;
        this.uuidSpace = uuidSpace;
    }

	public EquipmentDto () {
	}

	public java.util.UUID getUuidEquipment() {
        return uuidEquipment;
    }

    public String getType() {
        return type;
    }

    public java.util.UUID getUuidSpace () {
        return uuidSpace;
    }
}
