package esgi.com.exposition;

import esgi.infra.mysql.MealTrayOrderRepositoryImpl;
import esgi.common.dto.MealTrayOrderDto;
import esgi.common.dto.OrderDto;
import esgi.infra.mysql.UserRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;
import java.util.List;



@RestController
@RequestMapping (value = "/mealtrayorder", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class MealTrayOrderController {

    Logger logger = LoggerFactory.getLogger(MealTrayOrderController.class);

    @PostMapping ("/new")
    @ResponseStatus (org.springframework.http.HttpStatus.OK)
    public void MealTrayOrderRegistration(@RequestBody OrderDto orderDto){

        UserRepositoryImpl userRepository = new UserRepositoryImpl ();
        UUID uuidUser = userRepository.getUuidUserByNameAndSurname (orderDto.getNomUser (), orderDto.getSurnomUser () );

        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
	    UUID uuid = mealTrayOrderRepository.generateUUID ();

        mealTrayOrderRepository.saveOrder( uuid, uuidUser, "new", orderDto.getDate());
    }


    @GetMapping ("/{date}")
    @ResponseStatus (org.springframework.http.HttpStatus.OK)
    public @ResponseBody
    List<MealTrayOrderDto> getMealTrayOrder(@PathVariable("dateOrder") String dateOrder) {
        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        logger.debug ("MEALTRAYORDERCONTROLLER GETMEALTRAYORDER");
        return mealTrayOrderRepository.getOrderOfTheDay(dateOrder);
    }

  /*  @org.springframework.web.bind.annotation.GetMapping ("/updatestatus/{status}")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
    public @org.springframework.web.bind.annotation.ResponseBody
    java.util.List<esgi.common.dto.MealTrayOrderDto> updateMealTrayOrder(@org.springframework.web.bind.annotation.RequestBody esgi.common.dto.MealTrayOrderDto mealTrayOrderDto) {
        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        return mealTrayOrderRepository.updateStatusOrder(mealTrayOrderDto.getUuid(), mealTrayOrderDto.getStatus());
    }

    @org.springframework.web.bind.annotation.GetMapping ("/orders")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
    public @org.springframework.web.bind.annotation.ResponseBody
    java.util.List<esgi.common.dto.MealTrayOrderDto> getOrders() {

        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        return mealTrayOrderRepository.getOrders();
    }*/

}