package esgi.com.exposition;

import esgi.common.dto.BookingDto;
import esgi.common.dto.BookingRequestDto;
import esgi.infra.mysql.UserRepositoryImpl;
import esgi.use_case.Booking;
import esgi.use_case.LoanRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



@RestController
@org.springframework.web.bind.annotation.CrossOrigin ()
public class BookingController {
    //1 request
    Logger logger = LoggerFactory.getLogger(BookingController.class);

    @PostMapping ("/book")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void roomBooking(@RequestBody esgi.common.dto.BookingRequestDto bookingRequestDto){
        logger.debug ("BOOKINGCONTROLLER roomBooking");

        Booking booking = new Booking(bookingRequestDto.getType(), bookingRequestDto.getSpace(), bookingRequestDto.getDateStart(), bookingRequestDto.getDateEnd(), bookingRequestDto.getNameUser(), bookingRequestDto.getSurnameUser());

        booking.add();
    }


}