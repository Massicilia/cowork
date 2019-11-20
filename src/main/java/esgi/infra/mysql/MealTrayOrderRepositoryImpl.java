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
    public java.sql.Statement statement = null;
    java.sql.Connection connection;

	org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MealTrayOrderRepositoryImpl.class);

    void mysqlConnection() {
        connection = esgi.infra.mysql.DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<MealTrayOrderDto> getOrderOfTheDay(String dateOrder){
        mysqlConnection();
        logger.debug ("MEALTRAYORDERREPOIMPL GETORDEROFTHEDAY");
        List<esgi.common.dto.MealTrayOrderDto> mealTrayOrderDtos = new ArrayList<>();
        String uuidUserString;
        UUID uuidUser=null;
        String uuidString;
        UUID uuid=null;
        String status=null;

        String formatDateOrder = dateOrder.toString ();

        String getOrder ="SELECT UUID, uuidUser, status, dayOrder, monthOrder, yearOrder type FROM mealtray WHERE dayOrder = '" + Integer.parseInt(formatDateOrder.substring (8, 10)) + "' and monthOrder = '" + Integer.parseInt(formatDateOrder.substring (5, 7)) + "' and yearOrder = '" + Integer.parseInt(formatDateOrder.substring (0, 4)) + "'";

        try {
            ResultSet resultset = statement.executeQuery(getOrder);
            while (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuid = java.util.UUID.fromString(uuidString);
                uuidUserString = resultset.getString("userUUID");
                uuidUser = java.util.UUID.fromString(uuidUserString);
                status = resultset.getString("status");
                MealTrayOrderDto mealTrayOrderDto = new MealTrayOrderDto(uuid, uuidUser, status, dateOrder );
                mealTrayOrderDtos.add(mealTrayOrderDto);
                if (resultset == null) {
                    throw new esgi.common.exceptions.AnyMealTrayOrderFoundException ();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        esgi.infra.mysql.DbConnect.closeConnection(connection);
        return mealTrayOrderDtos;

    }

    @Override
    public java.util.List<esgi.common.dto.MealTrayOrderDto> getOrders(){
        mysqlConnection();

        java.util.List<esgi.common.dto.MealTrayOrderDto> mealTrayOrderDtos = new java.util.ArrayList<>();
        MealTrayOrderDto mealTrayOrderDto;
        String getMealTrayOrders = "SELECT UUID, uuidUser, status, dayOrder, monthOrder, yearOrder FROM mealtray " ;

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getMealTrayOrders);
            while (resultset.next()) {

                String uuid = resultset.getString("UUID");
                String uuiduser = resultset.getString("userUUID");
                String status = resultset.getString("status");

                java.time.LocalDate dateOrder = java.time.LocalDate.of(resultset.getInt ("yearOrder"), resultset.getInt ("monthOrder"), (resultset.getInt ("dayOrder")));


                mealTrayOrderDto = new MealTrayOrderDto (java.util.UUID.fromString(uuid), java.util.UUID.fromString(uuiduser), status, dateOrder);
                mealTrayOrderDtos.add(mealTrayOrderDto);
                if (resultset == null) {
                    throw new esgi.common.exceptions.AnyMealTrayOrderFoundException ();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        esgi.infra.mysql.DbConnect.closeConnection(connection);
        return mealTrayOrderDtos;
    }

    @Override
    public void updateStatusOrder(java.util.UUID uuid, String status){
        mysqlConnection();


        String setNewStatusToOrder = "UPDATE mealtrayorder SET status ='" + status + "' WHERE UUID = '" + uuid.toString () + "'";

        try {
            statement.executeUpdate (setNewStatusToOrder);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveOrder(java.util.UUID uuid, java.util.UUID UuidUser, String status, String dateOrder ){
            mysqlConnection();

            esgi.common.dto.MealTrayOrderDto mealTrayOrderDto = new esgi.common.dto.MealTrayOrderDto ();


            String postMealTrayOrder = "INSERT INTO mealtray (UUID, uuiduser, status, dayOrder, monthOrder, yearOrder)" +
                    " VALUES ( '" + uuid.toString() + "', '" +
                    UuidUser.toString() + "', '" +
                    status + "', '" +
                    Integer.parseInt(dateOrder.substring (8, 10)) + "', '" +
                    Integer.parseInt(dateOrder.substring (5, 7)) + "', '" +
                    Integer.parseInt(dateOrder.substring (0, 4)) + "')";


            try {
                statement.execute(postMealTrayOrder);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }

            DbConnect.closeConnection(connection);
        }

    public java.util.UUID generateUUID() {
        boolean uuidExist = true;
        java.util.UUID uuidOrder = java.util.UUID.randomUUID();
        while (uuidExist) {
            uuidOrder = java.util.UUID.randomUUID();
            List<esgi.common.dto.MealTrayOrderDto> mealTrayOrderDtos = this.getOrders();
            uuidExist = mealTrayOrderDtos.stream()
                    .map(esgi.common.dto.MealTrayOrderDto::getUuid)
                    .anyMatch(uuidOrder::equals);
        }
        return uuidOrder;
    }




}
