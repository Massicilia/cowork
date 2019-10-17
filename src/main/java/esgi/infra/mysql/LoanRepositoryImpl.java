package esgi.infra.mysql;

import esgi.use_case.ClientRepository;
import esgi.use_case.LoanRepository;
import esgi.common.dto.ClientFullDto;
import esgi.common.exceptions.ClientNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;

public class LoanRepositoryImpl implements LoanRepository {
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
    public void saveLoan(java.util.UUID uuidLoan, java.util.UUID UuidEquipment, java.util.UUID UuidClient) {
        mysqlConnection();
        String uuidString;

        String postLoanRequest = "INSERT INTO request ( UUID, type, client, equipment)" +
                " VALUES ( '" + 11 + "', 'loanrequest', '" + UuidClient.toString() + "', '"
                + UuidEquipment.toString() + "')";

        try {
            statement.execute(postLoanRequest);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        String setEquipmentNonAvailable = "UPDATE equipment" +
        "SET available = '0'"+
        "WHERE equipment.UUID = '" + UuidEquipment.toString() + "'";
        try {
            statement.execute(setEquipmentNonAvailable);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
    }

}
