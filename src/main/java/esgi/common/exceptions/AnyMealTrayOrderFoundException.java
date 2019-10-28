package esgi.common.exceptions;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "No meal tray order found")
public class AnyMealTrayOrderFoundException extends RuntimeException {
}
