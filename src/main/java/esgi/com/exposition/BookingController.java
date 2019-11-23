package esgi.com.exposition;

import esgi.common.dto.AvailableRoomDto;
import esgi.common.dto.RoomDto;
import esgi.common.dto.BookingRequestDto;
import esgi.infra.mysql.BookingRepositoryImpl;
import esgi.use_case.Booking;
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
    public void roomBooking(@RequestBody BookingRequestDto bookingRequestDto){
        logger.debug ("BOOKINGCONTROLLER roomBooking");

        Booking booking = new Booking(bookingRequestDto.getType(), bookingRequestDto.getSpace(), bookingRequestDto.getDateStart(), bookingRequestDto.getDateEnd(), bookingRequestDto.getNameUser(), bookingRequestDto.getSurnameUser());

        booking.add();
    }
    @GetMapping("/room/available")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public @ResponseBody
    RoomDto getAvailableRoom(@RequestBody AvailableRoomDto availableRoomDto) {
        BookingRepositoryImpl bookingRepository = new BookingRepositoryImpl ();

        return bookingRepository.getAvailableRoom (availableRoomDto.getType (), availableRoomDto.getSpace (), availableRoomDto.getDateStart ().toString (), availableRoomDto.getDateEnd ().toString ());
    }


}