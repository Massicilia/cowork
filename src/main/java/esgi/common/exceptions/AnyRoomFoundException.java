package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No room found")
public class AnyRoomFoundException extends RuntimeException {
}
