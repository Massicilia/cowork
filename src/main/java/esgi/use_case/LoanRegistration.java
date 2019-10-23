package esgi.use_case;

import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.mysql.LoanRepositoryImpl;
import java.util.UUID;

public class LoanRegistration {

    public String typeEquipment;
    public java.util.UUID uuidUser;
    java.util.Date dateLoanBegin;
    java.util.Date dateLoanEnd;

    public LoanRegistration(java.util.UUID uuidUser, String typeEquipment,
                            java.util.Date dateLoanBegin, java.util.Date dateLoanEnd){
        this.uuidUser = uuidUser;
        this.typeEquipment = typeEquipment;
        this.dateLoanBegin = dateLoanBegin;
        this.dateLoanEnd = dateLoanEnd;

    }

    public void register() {
        EquipmentRepositoryImpl equipmentRepository = new esgi.infra.mysql.EquipmentRepositoryImpl ();
        //avoir un equipment available de type x
        UUID uuidEquipment = equipmentRepository.getAvailableEquipmentByType(typeEquipment);

        LoanRepositoryImpl loanRepository = new LoanRepositoryImpl();
        loanRepository.saveLoan( uuidEquipment, uuidUser, dateLoanBegin, dateLoanEnd);
    }

}
