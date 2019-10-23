package esgi.com.exposition;

import esgi.common.dto.ClientDto;
import esgi.common.dto.ClientFullDto;
import esgi.infra.mysql.ClientRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping(value = "/client", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.DELETE, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.POST})
public class ClientController {

	@org.springframework.web.bind.annotation.GetMapping()
	@org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @org.springframework.web.bind.annotation.ResponseBody
	java.util.List<ClientFullDto> getClients() {

		ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();

		return clientRepository.getClients();
	}

	@GetMapping("/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ClientFullDto getClient(@PathVariable UUID uuid) {
		ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
		return clientRepository.getClient(uuid);
	}

	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean insertClient(@RequestBody String name, String surname,
	                                          String mail, boolean subscription) {
		ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
		ClientFullDto client = new esgi.common.dto.ClientFullDto ();
		client	=	clientRepository.generateUUID(client);
		client.setNameClient (name);
		client.setSurnameClient (surname);
		client.setMail (mail);
		client.setSubscription (subscription);
		return clientRepository.insertClient(client);
	}

	/*@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean updateClientSubscription(@RequestBody ClientFullDto client) {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.updateCandidate(candidate);
	}*/


/*	@GetMapping("/lessInterview")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<CandidateFullDto> getCandidatesLessInterview() {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.getCandidatesLessInterview();
	}


	@GetMapping("/sch/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	CandidateDto getCandidateForSchedule(@PathVariable UUID uuid) {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.getCandidateForSchedule(uuid);
	}

	@DeleteMapping("/delete/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public boolean deleteCandidate(@PathVariable UUID uuid) {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.deleteCandidate(uuid);
	}
*/

}
