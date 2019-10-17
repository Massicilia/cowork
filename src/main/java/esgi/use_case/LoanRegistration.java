package esgi.use_case;

import esgi.infra.mysql.ClientRepositoryImpl;
import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.mysql.LoanRepositoryImpl;
import esgi.common.dto.EquipmentDto;
import esgi.common.dto.LoanDto;
import java.util.UUID;

public class LoanRegistration {

    public String typeEquipment;
    public java.util.UUID UuidClient;
    public EquipmentRepositoryImpl equipmentRepository;

    public LoanRegistration(java.util.UUID UuidClient, EquipmentRepositoryImpl equipmentRepository,
                            String typeElement ){
        this.UuidClient = UuidClient;
        this.equipmentRepository = equipmentRepository;
        this.typeEquipment = typeEquipment;

    }

    public void register() {
        //avoir un equipment available de type x
        EquipmentDto equipment = equipmentRepository.getAvailableEquipmentByType(typeEquipment);
        //enregistrer un loan
	    java.util.UUID uuidLoan = null;
        LoanRepositoryImpl loanRepository = new LoanRepositoryImpl();
        loanRepository.saveLoan(uuidLoan, equipment.getUuidEquipment(), UuidClient);
    }

}
