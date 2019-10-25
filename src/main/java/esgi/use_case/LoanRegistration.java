package esgi.use_case;

import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.mysql.LoanRepositoryImpl;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class LoanRegistration {

    Logger logger = LoggerFactory.getLogger(esgi.use_case.LoanRegistration.class);

    String typeEquipment;
    UUID uuidUser;
    LocalDate dateLoanBegin;
    LocalDate dateLoanEnd;

    public LoanRegistration(UUID uuidUser, String typeEquipment,
                            LocalDate dateLoanBegin, LocalDate dateLoanEnd){
        this.uuidUser = uuidUser;
        this.typeEquipment = typeEquipment;
        this.dateLoanBegin = dateLoanBegin;
        this.dateLoanEnd = dateLoanEnd;

    }

    public void register() {

        logger.debug ("LOANREGISTRATION REGISTER");
        EquipmentRepositoryImpl equipmentRepository = new EquipmentRepositoryImpl ();
        //avoir un equipment available de type x
        UUID uuidEquipment = equipmentRepository.getAvailableEquipmentByType(typeEquipment);
        logger.debug ("LOANREGISTRATION REGISTER AFTER GETTING UUIDEQUIPMENT");
        LoanRepositoryImpl loanRepository = new LoanRepositoryImpl();
        logger.debug ("LOANREGISTRATION REGISTER AFTER LOANRESPOSITORYIMPL CREATION");
        loanRepository.saveLoan( uuidEquipment, uuidUser, dateLoanBegin, dateLoanEnd);
    }

}
