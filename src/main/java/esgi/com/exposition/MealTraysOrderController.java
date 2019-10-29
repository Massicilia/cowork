package esgi.com.exposition;

import esgi.use_case.MealTrayOrderRegistration;
import esgi.infra.mysql.MealTrayOrderRepositoryImpl;
import esgi.common.dto.MealTrayOrderRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping(value = "/mealtrayorder", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class MealTrayOrderController {

    Logger logger = LoggerFactory.getLogger(esgi.com.exposition.MealTrayOrderController.class);

    @PostMapping ("/new")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void MealTrayOrderRegistration(@RequestBody MealTrayOrderDto mealTrayOrderDto){

        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        mealTrayOrderRepository.saveOrder(mealTrayOrderDto.getUuid(), mealTrayOrderDto.getUserUuid(), mealTrayOrderDto.getStatus());
    }


    @GetMapping("/{date}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<MealTrayOrderDto> getMealTrayOrder(@PathVariable LocalDate dateOrder) {
        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        return mealTrayOrderRepository.getOrderOfTheDay(LocalDate dateOrder);
    }

    @GetMapping("/updatestatus/{status}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    List<MealTrayOrderDto> getMealTrayOrder(@RequestBody MealTrayOrderDto mealTrayOrderDto) {
        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        return mealTrayOrderRepository.UpdateStatusOrder(mealTrayOrderDto.getUserUuid(), mealTrayOrderDto.getStatus(), mealTrayOrderDto.dateOrder);
    }

    @GetMapping("/orders")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public @ResponseBody
    List<MealTrayOrderDto> getOrders() {

        MealTrayOrderRepositoryImpl mealTrayOrderRepository = new MealTrayOrderRepositoryImpl ();
        return mealTrayOrderRepository.getOrders();
    }

}