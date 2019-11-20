package esgi.common.dto;

import java.util.UUID;
import java.time.LocalDate;

public class LoanDto {


    private UUID uuidEquipment;
    private UUID uuidUser;
    private LocalDate dateLoanBegin;
    private LocalDate dateLoanEnd;

    public LoanDto( UUID uuidEquipment, UUID uuidUser, LocalDate dateLoanBegin, LocalDate dateLoanEnd){
                this.uuidEquipment = uuidEquipment;
        this.uuidUser = uuidUser;
        this.dateLoanBegin = dateLoanBegin;
        this.dateLoanEnd = dateLoanEnd;
    }

    public LoanDto () {
    }

    public UUID getUuidEquipment () {
        return uuidEquipment;
    }

    public void setUuidEquipment (final UUID uuidEquipment) {
        this.uuidEquipment = uuidEquipment;
    }

    public UUID getUuidUser () {
        return uuidUser;
    }

    public void setUuidUser (final UUID uuidUser) {
        this.uuidUser = uuidUser;
    }
}