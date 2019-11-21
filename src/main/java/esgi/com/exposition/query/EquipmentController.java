package esgi.com.exposition.query;
import esgi.common.dto.EquipmentDto;
import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.infra.DateFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.UUID;
import java.util.List;




@CrossOrigin()
@RestController

@RequestMapping(value = "/equipment", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class EquipmentController {
	//2 requests
	Logger logger = LoggerFactory.getLogger(esgi.com.exposition.query.EquipmentController.class);

	@GetMapping("/available/{type}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	UUID getAvailableEquipmentByType(@PathVariable String type) {
		EquipmentRepositoryImpl equipmentRepository = new EquipmentRepositoryImpl();

		return equipmentRepository.getAvailableEquipmentByType (type);
	}

	@GetMapping("/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	EquipmentDto getEquipment(@PathVariable UUID uuid) {

		EquipmentRepositoryImpl equipmentRepository = new EquipmentRepositoryImpl();

		return equipmentRepository.getEquipment (uuid);
	}

}
