package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No loan found")
public class AnyLoanFoundException extends RuntimeException {
}
