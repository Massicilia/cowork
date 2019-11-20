package esgi.use_case;

import esgi.common.dto.MealTrayOrderDto;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

public interface MealTrayOrderRepository {


    java.util.List<esgi.common.dto.MealTrayOrderDto> getOrderOfTheDay(String dateOrder);
    java.util.List<esgi.common.dto.MealTrayOrderDto> getOrders ();
    void saveOrder(java.util.UUID uuid, java.util.UUID UuidUser, String status, String dateOrder );
    void updateStatusOrder(java.util.UUID uuid, String status);

}
