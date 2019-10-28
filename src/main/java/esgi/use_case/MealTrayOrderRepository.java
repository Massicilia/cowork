package esgi.use_case;

import esgi.common.dto.MealTrayOrderDto;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

public interface MealTrayOrderRepository {


    esgi.common.dto.MealTrayOrderDto getUser(java.util.UUID uuid_order);
    java.util.List<esgi.common.dto.MealTrayOrderDto> getOrders();
    void saveOrder(UUID uuid_order, UUID UuidUser, String status);

}
