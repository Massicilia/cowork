package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No user found")
public class AnyUserFoundException extends RuntimeException {
}
