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
    public List<MealTrayOrderDto> getOrderOfTheDay(LocalDate dateOrder){
        mysqlConnection();
        List<MealTrayOrderDto> mealTrayOrderDtos = new ArrayList<>();
        String uuidUserString;
        java.util.UUID uuidUser=null;
        String status=null;

        String formatDateOrder = dateOrder.toString ();

        String getOrder ="SELECT userUUID, status, dayOrder, monthOrder, yearOrder type FROM mealtrayorder WHERE dayOrder = '" + Integer.parseInt(formatDateOrder.substring (8, 10)) + "' and monthOrder = '" + Integer.parseInt(formatDateOrder.substring (5, 7)) + "' and yearOrder = '" + Integer.parseInt(formatDateOrder.substring (0, 4) + "'";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getOrder);
            while (resultset.next()) {
                uuidUserString = resultset.getString("userUUID");
                uuidUser = java.util.UUID.fromString(uuidUserString);
                status = resultset.getString("status");
                MealTrayOrderDto mealTrayOrderDto = new MealTrayOrderDto(uuidUser, status, dateOrder );
                mealTrayOrderDtos.add(mealTrayOrderDto);
                if (resultset == null) {
                    throw new AnyMealTrayOrderFoundException ();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        DbConnect.closeConnection(connection);
        return mealTrayOrderDtos;

    }

    @Override
    public List<MealTrayOrderDto> getOrders(){
        mysqlConnection();

        List<MealTrayOrderDto> mealTrayOrderDtos = new ArrayList<>();
        LoanDto mealTrayOrderDto;
        String getMealTrayOrders = "SELECT userUUID, status, dayOrder, monthOrder, yearOrder FROM mealtrayorder " ;

        try {
            ResultSet resultset = statement.executeQuery(getMealTrayOrders);
            while (resultset.next()) {

                String uuiduser = resultset.getString("userUUID");
                String status = resultset.getString("status");

                LocalDate dateOrder = LocalDate.of(resultset.getInt ("yearOrder"), resultset.getInt ("monthOrder"), (resultset.getInt ("dayOrder")));

                mealTrayOrderDto = new MealTrayOrderDto (UUID.fromString(uuiduser), status, dateOrder);
                mealTrayOrderDtos.add(mealTrayOrderDto);
                if (resultset == null) {
                    throw new AnyMealTrayOrderFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return mealTrayOrderDtos;
    }

    @Override
    public void saveOrder( UUID Uuid, UUID UuidUser, String status, LocalDate dateOrder ){
            mysqlConnection();

            MealTrayOrderDto mealTrayOrderDto = new MealTrayOrderDto ();

            String formatDateOrder = dateOrder.toString ();

            String postMealTrayOrder = "INSERT INTO mealtrayorder (userUUID, status, dayOrder, monthOrder, yearOrder)" +
                    " VALUES ( '" + UuidUser.toString() + "', '" +
                    status + "', '" +
                    Integer.parseInt(formatDateOrder.substring (8, 10)) + "', '" +
                    Integer.parseInt(formatDateOrder.substring (5, 7)) + "', '" +
                    Integer.parseInt(formatDateOrder.substring (0, 4)) + "')";


            try {
                statement.execute(postMealTrayOrder);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DbConnect.closeConnection(connection);
        }

    }




}
