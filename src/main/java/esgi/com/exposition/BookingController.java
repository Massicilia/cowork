package esgi.com.exposition;

import esgi.common.dto.LoanRequestDto;
import esgi.infra.mysql.UserRepositoryImpl;
import esgi.use_case.Booking;
import esgi.use_case.LoanRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@org.springframework.web.bind.annotation.CrossOrigin ()
public class BookingController {

    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping ("/book")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void roomBooking(@RequestBody BookingDto bookingDto){
        Booking booking = new Booking(bookingDto.getType(), bookingDto.getSpace(), bookingDto.getDateStart(), bookingDto.getDateEnd(), bookingDto.getUuidUser());

        booking.add();
    }


}