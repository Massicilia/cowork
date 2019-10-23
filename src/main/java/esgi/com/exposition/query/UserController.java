package esgi.com.exposition.query;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping(value = "/user", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.DELETE, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.POST})
public class UserController {

	@org.springframework.web.bind.annotation.GetMapping()
	@org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @org.springframework.web.bind.annotation.ResponseBody
	java.util.List<esgi.common.dto.UserFullDto> getUsers() {

		esgi.infra.mysql.UserRepositoryImpl userRepository = new esgi.infra.mysql.UserRepositoryImpl ();

		return userRepository.getUsers();
	}

	@GetMapping("/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	esgi.common.dto.UserFullDto getUser(@PathVariable UUID uuid) {
		esgi.infra.mysql.UserRepositoryImpl userRepository = new esgi.infra.mysql.UserRepositoryImpl ();
		return userRepository.getUser(uuid);
	}

	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean insertUser(@RequestBody String name, String surname,
	                                          String mail, int subscription) {
		esgi.infra.mysql.UserRepositoryImpl userRepository = new esgi.infra.mysql.UserRepositoryImpl ();
		esgi.common.dto.UserFullDto user = new esgi.common.dto.UserFullDto ();
		user	=	userRepository.generateUUID(user);
		user.setName (name);
		user.setSurname (surname);
		user.setMail (mail);
		user.setSubscription (subscription);
		return userRepository.insertUser(user);
	}

	/*@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean updateClientSubscription(@RequestBody UserFullDto client) {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.updateCandidate(candidate);
	}*/

}
