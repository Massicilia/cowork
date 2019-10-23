package esgi.infra.mysql;

import esgi.use_case.LoanRepository;
import java.util.UUID;

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
        String getLoans = "SELECT UUID, uuiduser, uuidequipment, dateLoanBegin, dateLoanEnd "+
                "FROM loan " ;

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getLoans);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String uuiduser = resultset.getString("uuiduser");
                String uuidequipment = resultset.getString("uuidequipment");
                java.util.Date dateLoanBegin =resultset.getDate ("dateLoanBegin");
                java.util.Date dateLoanEnd =resultset.getDate ("dateLoanEnd");
                loanDto = new esgi.common.dto.LoanDto (UUID.fromString(uuidString), UUID.fromString(uuiduser),
                        UUID.fromString(uuidequipment), dateLoanBegin, dateLoanEnd);
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
    public void saveLoan(java.util.UUID uuidEquipment, java.util.UUID uuidUser,
                         java.util.Date dateLoanBegin, java.util.Date dateLoanEnd) {
        mysqlConnection();
        esgi.common.dto.LoanDto loanDto = new esgi.common.dto.LoanDto ();
        loanDto = generateUUID(loanDto);
        java.util.UUID uuidLoan = loanDto.getUuidLoan ();
        String postLoanRequest = "INSERT INTO request ( UUID, uuiduser, uuidequipment, dateLoanBegin, dateLoanEnd)" +
                " VALUES ( '" + uuidLoan.toString () + "', 'loanrequest', '" + uuidUser.toString() + "', '"
                + uuidEquipment.toString() + dateLoanBegin + "', '" + dateLoanEnd + "')";

        try {
            statement.execute(postLoanRequest);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        String setEquipmentNonAvailable = "UPDATE equipment" +
        "SET available = '0'"+
        "AND dateAvailibility = '" + dateLoanEnd + "'" +
        "AND uuidLoanRequester = '" + uuidUser + "'" +
        "AND statut = NOT AVAILABLE" +
        "WHERE UUID = '" + uuidEquipment.toString() + "'";
        try {
            statement.execute(setEquipmentNonAvailable);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
    }

}
