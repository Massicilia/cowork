package esgi.com.exposition.query;

import esgi.common.dto.TicketFullDto;
import esgi.infra.mysql.TicketRepositoryImpl;
import esgi.infra.mysql.UserRepositoryImpl;
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
@RequestMapping (value = "/ticket", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class TicketController {
	//6 requests
	Logger logger = LoggerFactory.getLogger(esgi.com.exposition.query.TicketController.class);

	@GetMapping("/tickets")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<TicketFullDto> getTickets() {

		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTickets();
	}

	@GetMapping("/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	esgi.common.dto.TicketCreationDto getTicket(@PathVariable UUID uuid) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicket(uuid);
	}

	@PostMapping("/insertTicket")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void insertTicket(@RequestBody esgi.common.dto.TicketCreationDto ticketDto) {

		UserRepositoryImpl userRepository =new UserRepositoryImpl ();

		UUID uuidAssigned = userRepository.getUuidUserByNameAndSurname (ticketDto.getNameAssignee (), ticketDto.getSurnameAssignee ());
		UUID uuidCreator = userRepository.getUuidUserByNameAndSurname (ticketDto.getNameCreator (), ticketDto.getSurnameCreator () );

		boolean assigneeEmployee = userRepository.isEmployee (uuidAssigned);
		boolean creatorEmployee = userRepository.isEmployee (uuidCreator);

		TicketFullDto ticket = new TicketFullDto();
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		ticket = ticketRepository.generateUUID (ticket);

		if(assigneeEmployee && creatorEmployee){
			ticketRepository.insertTicket(ticketDto, ticket, uuidCreator, uuidAssigned);
		}


	}

	@GetMapping("/assigned/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<esgi.common.dto.TicketCreationDto> getTicketByAssigneeUuid(@PathVariable UUID uuid) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicketsByAssigneeUUID (uuid);
	}

	@GetMapping("/creator/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<esgi.common.dto.TicketCreationDto> getTicketByCreatorUuid(@PathVariable UUID uuid) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicketsByCreatorUUID (uuid);
	}

	@PostMapping("/statuschange")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void updateTicketStatus(@RequestBody esgi.common.dto.TicketDto ticket) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		ticketRepository.updateTicketStatus ( ticket.getUuidTicket(), ticket.getStatus());
	}


}
