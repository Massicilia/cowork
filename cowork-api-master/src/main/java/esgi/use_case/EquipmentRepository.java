package esgi.use_case;

import esgi.common.dto.EquipmentDto;

public interface EquipmentRepository {

    EquipmentDto getEquipment(java.util.UUID uuidEquipment);
    java.util.UUID getAvailableEquipmentByType (String typeEquipment);

 /*
    boolean deleteEquipment(UUID uuidEquipment);

    boolean insertEquipment(EquipmentDto equipmentDto);*/
}
