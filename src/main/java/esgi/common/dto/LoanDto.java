package esgi.common.dto;

import java.util.UUID;

public class LoanDto {

    private java.util.UUID uuidLoan;
    private java.util.UUID uuidEquipment;
    private java.util.UUID uuidUser;
    private java.util.Date dateLoanBegin;
    private java.util.Date dateLoanEnd;

    public LoanDto(java.util.UUID uuidLoan, java.util.UUID uuidEquipment, java.util.UUID uuidUser, java.util.Date dateLoanBegin, java.util.Date dateLoanEnd){
        this.uuidLoan = uuidLoan;
        this.uuidEquipment = uuidEquipment;
        this.uuidUser = uuidUser;
        this.dateLoanBegin = dateLoanBegin;
        this.dateLoanEnd = dateLoanEnd;
    }

    public LoanDto(){}

    public java.util.UUID getUuidLoan() {
        return uuidLoan;
    }

    public void setUuidLoan (final java.util.UUID uuidLoan) {
        this.uuidLoan = uuidLoan;
    }
}