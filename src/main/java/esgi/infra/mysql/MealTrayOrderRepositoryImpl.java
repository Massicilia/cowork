package esgi.infra.mysql;

import esgi.use_case.MealTrayOrderRepository;
import esgi.common.dto.MealTrayOrderDto;
import esgi.common.exceptions.AnyMealTrayOrderFoundException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class MealTrayOrderRepositoryImpl implements MealTrayOrderRepository {
    public Statement statement = null;
    Connection connection;

	Logger logger = LoggerFactory.getLogger(esgi.infra.mysql.MealTrayOrderRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public MealTrayOrderDto getUser(UUID uuid_order){


    }

    @Override
    public List<MealTrayOrderDto> getOrders(){

    }

    @Override
    public void saveOrder(UUID uuid_order, UUID UuidUser, String status){

    }




}
