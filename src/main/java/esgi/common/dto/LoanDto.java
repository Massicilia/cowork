package esgi.common.dto;

import java.util.UUID;

public class LoanDto {

    private java.util.UUID uuidLoan;
    private String loanType;
    private java.util.UUID uuidEquipment;
    private java.util.UUID uuidClient;

    public LoanDto(java.util.UUID uuidLoan, String loanType, java.util.UUID uuidEquipment, java.util.UUID uuidClient){
        this.uuidLoan = uuidLoan;
        this.loanType = loanType;
        this.uuidEquipment = uuidEquipment;
        this.uuidClient = uuidClient;
    }

    public LoanDto(){}

    public java.util.UUID getUuidLoan() {
        return uuidLoan;
    }

    public void setUuidLoan (final java.util.UUID uuidLoan) {
        this.uuidLoan = uuidLoan;
    }
}