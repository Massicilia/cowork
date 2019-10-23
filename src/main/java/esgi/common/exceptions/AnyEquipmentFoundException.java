package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No equipment found")
public class AnyEquipmentFoundException extends RuntimeException {
}
