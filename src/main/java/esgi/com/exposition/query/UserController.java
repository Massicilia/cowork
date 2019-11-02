package esgi.com.exposition.query;

import esgi.common.dto.UserFullDto;
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
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class UserController {

	Logger logger = LoggerFactory.getLogger(esgi.com.exposition.query.UserController.class);

	@GetMapping("/users")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public @ResponseBody
	List<UserFullDto> getUsers() {
logger.debug ("GETUSERS");
		UserRepositoryImpl userRepository = new UserRepositoryImpl ();

		return userRepository.getUsers();
	}

	@GetMapping("/{uuid}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	UserFullDto getUser(@PathVariable UUID uuid) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl ();
		return userRepository.getUser(uuid);
	}

	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean insertUser(@RequestBody UserFullDto user){

		UserRepositoryImpl userRepository = new UserRepositoryImpl ();
		user	=	userRepository.generateUUID(user);

		return userRepository.insertUser(user);
	}

	@org.springframework.web.bind.annotation.GetMapping ("/auth")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public String getUserAuth(@RequestBody esgi.common.dto.UserDto user) {

		UserRepositoryImpl userRepository = new UserRepositoryImpl ();
		String type = userRepository.getUserAuth (user.getIdentifiant(), user.getPassword());
		return type;
	}

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	java.util.UUID getUUIDUserByNameAndSurname(@RequestBody UserFullDto user) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl ();
		return userRepository.getUuidUserByNameAndSurname(user.getName (), user.getSurname ());
	}



	/*@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody boolean updateClientSubscription(@RequestBody UserFullDto client) {
		CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
		return candidateRepository.updateCandidate(candidate);
	}*/

}
