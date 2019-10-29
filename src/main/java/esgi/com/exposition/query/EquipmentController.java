package esgi.com.exposition.query;

@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController

public class EquipmentController {

	@org.springframework.web.bind.annotation.GetMapping("/available/{type}")
	@org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @org.springframework.web.bind.annotation.ResponseBody
	java.util.UUID getAvailableEquipmentByType(@org.springframework.web.bind.annotation.RequestBody String type) {

		esgi.infra.mysql.EquipmentRepositoryImpl equipmentRepository = new esgi.infra.mysql.EquipmentRepositoryImpl();

		return equipmentRepository.getAvailableEquipmentByType (type);
	}

}
