package esgi.infra.mysql;
import esgi.use_case.EquipmentRepository;
import esgi.common.dto.EquipmentDto;
import esgi.common.exceptions.EquipmentNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EquipmentRepositoryImpl implements EquipmentRepository {
    public java.sql.Statement statement = null;
    java.sql.Connection connection;

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EquipmentDto getEquipment(java.util.UUID uuid_equipment){
        mysqlConnection();
        String uuidString;
        String uuidLoanRequesterString;
        java.util.UUID uuidEquipment=null;
        String type=null;
        int available=0;
        java.util.Date dateAvailability = null;
        String dateAvailabilityString = null;
        java.util.UUID uuidLoanRequester = null;
        String statut = null;


        String getEquipment = "SELECT equipment.UUID, equipment.type, equipment.available, equipment.dateAvailability, equipment.dateAvailability, equipment.uuidLoanRequester, equipment.statut"+
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
                dateAvailability =resultset.getDate ("dateAvailability");

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
    public java.util.UUID getAvailableEquipmentByType(String typeEquipment){
        mysqlConnection();
        String uuidString;
        java.util.UUID uuidEquipment=null;

        String getAvailableEquipmentByType = "SELECT UUID"+
                "FROM equipment" +
                "WHERE type = " + "'" + typeEquipment + "' and available =" +1;

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getAvailableEquipmentByType);
            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuidEquipment = java.util.UUID.fromString(uuidString);
            } else {
                throw new EquipmentNotFoundException();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidEquipment;
    }

}
