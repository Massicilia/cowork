package esgi.common.dto;

import java.util.UUID;

public class LoanDto {

    private java.util.UUID uuidLoan;
    private java.util.UUID uuidEquipment;
    private java.util.UUID uuidClient;

    public LoanDto(java.util.UUID uuidLoan, java.util.UUID uuidEquipment, java.util.UUID uuidClient){
        this.uuidLoan = uuidLoan;
        this.uuidEquipment = uuidEquipment;
        this.uuidClient = uuidClient;
    }

    public java.util.UUID getUuidLoan() {
        return uuidLoan;
    }
}
