package esgi.infra.mysql;
import esgi.common.exceptions.AnyEquipmentFoundException;
import esgi.use_case.EquipmentRepository;
import esgi.common.dto.EquipmentDto;
import esgi.common.exceptions.EquipmentNotFoundException;
import esgi.infra.DateFormat;
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
	DateFormat dateFormat = new DateFormat ();

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
        String uuidSpaceString;
        String uuidLoanRequesterString;
        java.util.UUID uuidEquipment=null;
        java.util.UUID uuidSpace=null;
        String type=null;
        int available=0;
        java.util.UUID uuidLoanRequester = null;
        String statut = null;

	    LocalDate dateAvailability = null;


        String getEquipment = "SELECT UUID, type, available, dayAvailability, monthAvailability, yearAvailability, uuidLoanRequester, statut, space FROM equipment WHERE UUID = '" + uuid_equipment.toString() + "' ";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getEquipment);
            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuidEquipment = java.util.UUID.fromString(uuidString);
                uuidSpaceString = resultset.getString("space");
                uuidSpace = java.util.UUID.fromString(uuidSpaceString);
                type = resultset.getString("type");
                available = resultset.getInt ("available");
                uuidLoanRequesterString = available==1?null : resultset.getString ("uuidLoanRequester");
                uuidLoanRequester = java.util.UUID.fromString(uuidLoanRequesterString);
                statut = resultset.getString ("statut");
	            if(resultset.getInt ("yearAvailability") != 0 || resultset.getInt ("monthAvailability")!= 0 || resultset.getInt ("dayAvailability") != 0){

		            String dateString = resultset.getInt ("yearAvailability") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthAvailability")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayAvailability"));
		            dateAvailability = LocalDate.parse(dateString);
	            }
            } else {
                throw new EquipmentNotFoundException();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        EquipmentDto equipmentDto = new EquipmentDto(uuidEquipment, type, available, dateAvailability,
                uuidLoanRequester, statut, uuidSpace);

        DbConnect.closeConnection(connection);
        return equipmentDto;
    }



    @Override
    public UUID getAvailableEquipmentByType(String typeEquipment){
        mysqlConnection();
        String uuidString;
        UUID uuidEquipment= new java.util.UUID (0, 0);

        String getAvailableEquipmentByType = "SELECT UUID FROM equipment WHERE type = '" + typeEquipment + "' and available =" +1;
	    logger.debug ("equipmentrepoimpl getAvailableEquipmentByType " + getAvailableEquipmentByType);
        try {
            ResultSet resultset = statement.executeQuery(getAvailableEquipmentByType);
            if (resultset.next()) {
                uuidString = resultset.getString ("UUID");
                uuidEquipment = UUID.fromString(uuidString);

            } else {
                throw new AnyEquipmentFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidEquipment;
    }

}
