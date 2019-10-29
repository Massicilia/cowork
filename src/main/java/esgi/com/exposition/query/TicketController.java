package esgi.com.exposition.query;

import esgi.common.dto.TicketFullDto;
import esgi.infra.mysql.TicketRepositoryImpl;
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
	TicketFullDto getTicket(@PathVariable UUID uuid) {
		logger.debug ("uuidTICKET"+uuid);
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicket(uuid);
	}


	@PostMapping("/insertTicket")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void insertTicket(@RequestBody TicketFullDto ticketDto) {
		logger.debug ("TICKETINSERT" + ticketDto.getNameAssignee ().toString ());
		esgi.infra.mysql.UserRepositoryImpl userRepository =new esgi.infra.mysql.UserRepositoryImpl ();
		logger.debug ("TICKETINSERT USERREPO");
		boolean assigneeEmployee = userRepository.isEmployee (ticketDto.getNameAssignee ());
		boolean creatorEmployee = userRepository.isEmployee (ticketDto.getNameCreator ());
		logger.debug ("assigneeEmployee" + assigneeEmployee);
		logger.debug ("creatorEmployee" + creatorEmployee);
		if(assigneeEmployee && creatorEmployee){
			logger.debug ("TICKETINSERT isemployee");
			TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
			logger.debug ("TICKETINSERT TICKETREPO");
			ticketDto	=	ticketRepository.generateUUID(ticketDto);
			logger.debug ("TICKETINSERT GENERATEUUID");
			ticketRepository.insertTicket(ticketDto);
		}


	}

	@GetMapping("/ticket/assigned/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<TicketFullDto> getTicketByAssigneeUuid(@PathVariable UUID uuid) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicketsByAssigneeUUID (uuid);
	}

	@GetMapping("/ticket/creator/{uuid}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<TicketFullDto> getTicketByCreatorUuid(@PathVariable UUID uuid) {
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		return ticketRepository.getTicketsByCreatorUUID (uuid);
	}

	@GetMapping("/statuschange")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void updateTicketStatus(@RequestBody esgi.common.dto.TicketDto ticket) {
		logger.debug ("TICKETCONTROLLER UPDATESTATUS");
		TicketRepositoryImpl ticketRepository = new TicketRepositoryImpl ();
		ticketRepository.updateTicketStatus ( ticket.getUuidTicket(), ticket.getStatus());
	}


}
