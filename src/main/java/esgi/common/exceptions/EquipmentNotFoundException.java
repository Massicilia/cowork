package esgi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ResponseStatus(code = org.springframework.http.HttpStatus.OK, reason = "Client not found")
public class EquipmentNotFoundException extends RuntimeException {
}
