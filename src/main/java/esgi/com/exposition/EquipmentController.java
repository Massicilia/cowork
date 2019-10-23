package esgi.com.exposition;

@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping(value = "/client", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.DELETE, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.POST})
public class EquipmentController {
	
	@org.springframework.web.bind.annotation.GetMapping("/available/{type}")
	@org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @org.springframework.web.bind.annotation.ResponseBody
	java.util.UUID getAvailableEquipmentByType(@org.springframework.web.bind.annotation.RequestBody String type) {

		esgi.infra.mysql.EquipmentRepositoryImpl equipmentRepository = new esgi.infra.mysql.EquipmentRepositoryImpl();

		return equipmentRepository.getAvailableEquipmentByType (type);
	}

}
