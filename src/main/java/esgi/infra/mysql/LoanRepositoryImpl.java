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
    public java.util.List<esgi.common.dto.LoanDto> getLoans() {
        mysqlConnection();
        java.util.List<esgi.common.dto.LoanDto> loanDtos = new java.util.ArrayList<>();
        esgi.common.dto.LoanDto loanDto;
        String getLoans = "SELECT UUID, type, client, equipment "+
                "FROM request " ;

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getLoans);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String type = resultset.getString("type");
                String client = resultset.getString("client");
                String equipment = resultset.getString("equipment");
                loanDto = new esgi.common.dto.LoanDto (UUID.fromString(uuidString), type,UUID.fromString(client), UUID.fromString(equipment));
                loanDtos.add(loanDto);
                if (resultset == null) {
                    throw new esgi.common.exceptions.AnyLoanFoundException ();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return loanDtos;
    }


    public esgi.common.dto.LoanDto generateUUID(esgi.common.dto.LoanDto loan) {
        boolean uuidExist = true;
        UUID uuidLoan = UUID.randomUUID();
        while (uuidExist) {
            uuidLoan = UUID.randomUUID();
            java.util.List<esgi.common.dto.LoanDto> loanDtos = this.getLoans();
            uuidExist = loanDtos.stream()
                    .map(esgi.common.dto.LoanDto::getUuidLoan)
                    .anyMatch(uuidLoan::equals);
        }
        loan.setUuidLoan(uuidLoan);
        return loan;
    }

    @Override
    public void saveLoan( java.util.UUID uuidEquipment, java.util.UUID uuidClient) {
        mysqlConnection();
        esgi.common.dto.LoanDto loanDto = new esgi.common.dto.LoanDto ();
        loanDto = generateUUID(loanDto);
        java.util.UUID uuidLoan = loanDto.getUuidLoan ();
        String postLoanRequest = "INSERT INTO request ( UUID, type, client, equipment)" +
                " VALUES ( '" + uuidLoan.toString () + "', 'loanrequest', '" + uuidClient.toString() + "', '"
                + uuidEquipment.toString() + "')";

        try {
            statement.execute(postLoanRequest);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        String setEquipmentNonAvailable = "UPDATE equipment" +
        "SET available = '0'"+
        "WHERE UUID = '" + uuidEquipment.toString() + "'";
        try {
            statement.execute(setEquipmentNonAvailable);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
    }

}
