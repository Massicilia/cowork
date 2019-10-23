package esgi.use_case;

import esgi.common.dto.ClientDto;
import esgi.common.dto.EquipmentDto;

import java.util.List;
import java.util.UUID;

public interface EquipmentRepository {

    EquipmentDto getEquipment(java.util.UUID uuidEquipment);
    java.util.UUID getAvailableEquipmentByType (String typeEquipment);

 /*
    boolean deleteEquipment(UUID uuidEquipment);

    boolean insertEquipment(EquipmentDto equipmentDto);*/
}
