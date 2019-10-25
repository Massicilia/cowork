package esgi.infra.mysql;
import esgi.use_case.EquipmentRepository;
import esgi.common.dto.EquipmentDto;
import esgi.common.exceptions.EquipmentNotFoundException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import java.sql.SQLException;
import java.time.LocalDate;

import static javax.swing.UIManager.getInt;

public class EquipmentRepositoryImpl implements EquipmentRepository {
    public Statement statement = null;
    Connection connection;

    Logger logger = LoggerFactory.getLogger(esgi.infra.mysql.EquipmentRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EquipmentDto getEquipment(UUID uuid_equipment){
        mysqlConnection();
        String uuidString;
        String uuidLoanRequesterString;
        java.util.UUID uuidEquipment=null;
        String type=null;
        int available=0;
        java.util.UUID uuidLoanRequester = null;
        String statut = null;

        //AVAILABILITYDATE
        int dayAvailability = 0;
        int monthAvailability = 0;
        int yearAvailability = 0;
	    LocalDate dateAvailability = null;


        String getEquipment = "SELECT equipment.UUID, equipment.type, equipment.available, equipment.dayAvailability, equipment.monthAvailability, equipment.yearAvailability, equipment.uuidLoanRequester, equipment.statut"+
                            "FROM equipment" +
                            "WHERE equipment.UUID = " + "'" + uuid_equipment.toString() + "' ";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getEquipment);
            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuidEquipment = java.util.UUID.fromString(uuidString);
                type = resultset.getString("type");
                available = resultset.getInt ("available");
                uuidLoanRequesterString = resultset.getString ("uuidLoanRequester");
                uuidLoanRequester = java.util.UUID.fromString(uuidLoanRequesterString);
                statut = resultset.getString ("statut");
                dayAvailability =resultset.getInt("dayAvailability");
                monthAvailability =resultset.getInt("monthAvailability");
                yearAvailability =resultset.getInt("yearAvailability");
                String dateString = yearAvailability + "-" + monthAvailability + "-" + dayAvailability; ;
                //java.time.format.DateTimeFormatter format = java.time.format.DateTimeFormatter.ofPattern("yyyy-mm-dd");
	            dateAvailability = java.time.LocalDate.parse(dateString);//, format);

            } else {
                throw new EquipmentNotFoundException();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        EquipmentDto equipmentDto = new EquipmentDto(uuidEquipment, type, available, dateAvailability,
                uuidLoanRequester, statut);

        DbConnect.closeConnection(connection);
        return equipmentDto;
    }



    @Override
    public UUID getAvailableEquipmentByType(String typeEquipment){
        mysqlConnection();
        String uuidString;
        UUID uuidEquipment= new java.util.UUID (0, 0);

        logger.debug("EQUIPMENTREPOSITORYIMPL GETAVAILABLEQUIPMENTBYTYPE");
        String getAvailableEquipmentByType = "SELECT UUID FROM equipment WHERE type = '" + typeEquipment + "' and available =" +1;

        try {
            ResultSet resultset = statement.executeQuery(getAvailableEquipmentByType);
            if (resultset.next()) {
                uuidString = resultset.getString ("UUID");
                logger.debug ("UUIDSTRING " + uuidString);
                uuidEquipment = UUID.fromString(uuidString);
                logger.debug ("UUIDEQUIPMENT " + uuidEquipment);
            } else {
                throw new EquipmentNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidEquipment;
    }

}
