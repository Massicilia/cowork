package esgi.common.dto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class LoanRequestDto {

    Logger logger = LoggerFactory.getLogger(esgi.common.dto.LoanRequestDto.class);
    private String typeEquipment;
    private String nameUser;
    private String surnameUser;
    private LocalDate dateLoanBegin;
    private LocalDate dateLoanEnd;

    public LoanRequestDto (final String typeEquipment, final String nameUser, final String surnameUser, final LocalDate dateLoanBegin, final LocalDate dateLoanEnd) {

        this.typeEquipment = typeEquipment;
        this.nameUser = nameUser;
        this.surnameUser = surnameUser;
        this.dateLoanBegin = dateLoanBegin;
        this.dateLoanEnd = dateLoanEnd;
    }


    public LoanRequestDto (){};

    public String getSurnameUser () {
        return surnameUser;
    }

    public String getTypeEquipment () {
        return typeEquipment;
    }

    public String getNameUser () {
        return nameUser;
    }

    public LocalDate getDateLoanBegin () {
        return dateLoanBegin;
    }

    public LocalDate getDateLoanEnd () {
        return dateLoanEnd;
    }
}
