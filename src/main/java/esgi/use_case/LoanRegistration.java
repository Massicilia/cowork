package esgi.use_case;

import esgi.infra.mysql.ClientRepositoryImpl;
import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.mysql.LoanRepositoryImpl;
import esgi.common.dto.EquipmentDto;
import esgi.common.dto.LoanDto;
import java.util.UUID;

public class LoanRegistration {

    public String typeEquipment;
    public java.util.UUID uuidClient;

    public LoanRegistration(java.util.UUID uuidClient, String typeElement ){
        this.uuidClient = uuidClient;
        this.typeEquipment = typeEquipment;

    }

    public void register() {
        EquipmentRepositoryImpl equipmentRepository = new esgi.infra.mysql.EquipmentRepositoryImpl ();
        //avoir un equipment available de type x
        UUID uuidEquipment = equipmentRepository.getAvailableEquipmentByType(typeEquipment);

        LoanRepositoryImpl loanRepository = new LoanRepositoryImpl();
        loanRepository.saveLoan( uuidEquipment, uuidClient);
    }

}
