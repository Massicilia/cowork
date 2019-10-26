package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No ticket found")
public class AnyTicketFoundException extends RuntimeException {
}
