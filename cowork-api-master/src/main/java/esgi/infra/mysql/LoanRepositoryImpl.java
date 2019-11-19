package esgi.infra.mysql;

import esgi.use_case.LoanRepository;
import esgi.common.dto.LoanDto;
import esgi.common.exceptions.AnyLoanFoundException;
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

public class LoanRepositoryImpl implements LoanRepository {
    public Statement statement = null;
    Connection connection;

	Logger logger = LoggerFactory.getLogger(esgi.infra.mysql.LoanRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LoanDto> getLoans() {
        mysqlConnection();
	    logger.debug ("LOANREPOSITORYIMPL GETLOANS BEFORE ANY INSTRUCTION");
        List<LoanDto> loanDtos = new ArrayList<>();
        LoanDto loanDto;
        String getLoans = "SELECT uuiduser, uuidequipment, dayLoanBegin, monthLoanBegin, yearLoanBegin, dayLoanEnd, monthLoanEnd, yearLoanEnd FROM loan " ;

        try {
            ResultSet resultset = statement.executeQuery(getLoans);
            while (resultset.next()) {

                String uuiduser = resultset.getString("uuiduser");
                String uuidequipment = resultset.getString("uuidequipment");
                logger.debug ("LOANREPOSITORYIMPL GETLOANS AFTER GETTING DB DATAS");
	            LocalDate dateLoanBegin = LocalDate.of(resultset.getInt ("yearLoanBegin"), resultset.getInt ("monthLoanBegin"), (resultset.getInt ("dayLoanBegin")));
	            LocalDate dateLoanEnd = LocalDate.of(resultset.getInt ("yearLoanEnd"), resultset.getInt ("monthLoanEnd"), (resultset.getInt ("dayLoanEnd")));

	            logger.debug ("LOANREPOSITORYIMPL GETLOANS AFTER DATES PARSING");
                loanDto = new LoanDto (UUID.fromString(uuiduser),
                        UUID.fromString(uuidequipment), dateLoanBegin, dateLoanEnd);
                loanDtos.add(loanDto);
                if (resultset == null) {
                    throw new AnyLoanFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return loanDtos;
    }


    @Override
    public void saveLoan(UUID uuidEquipment, UUID uuidUser,
                         LocalDate dateLoanBegin, LocalDate dateLoanEnd) {
        mysqlConnection();
	    logger.debug ("LOANREPOSITORYIMPL SAVELOAN BEFORE ANY INSTRUCTION");
        LoanDto loanDto = new LoanDto ();

        String formatDateLoanBegin = dateLoanBegin.toString ();
        String formatDateLoanEnd = dateLoanEnd.toString ();

        String postLoanRequest = "INSERT INTO loan ( uuiduser, uuidequipment, dayLoanBegin, monthLoanBegin, yearLoanBegin, dayLoanEnd, monthLoanEnd, yearLoanEnd)" +
                " VALUES ( '" + uuidUser.toString() + "', '" +
                uuidEquipment.toString() + "', '" +
                Integer.parseInt(formatDateLoanBegin.substring (8, 10)) + "', '" +
                Integer.parseInt(formatDateLoanBegin.substring (5, 7)) + "', '" +
                Integer.parseInt(formatDateLoanBegin.substring (0, 4)) + "', '" +
                Integer.parseInt(formatDateLoanEnd.substring (8, 10)) + "', '" +
                Integer.parseInt(formatDateLoanEnd.substring (5, 7)) + "', '" +
                Integer.parseInt(formatDateLoanEnd.substring (0, 4)) + "')";


        try {
            statement.execute(postLoanRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String setEquipmentNonAvailable = "UPDATE equipment SET available ='" + "0" + "' AND dayAvailability = '" + Integer.parseInt(formatDateLoanEnd.substring (8, 10)) + "' AND monthAvailability = '" + Integer.parseInt(formatDateLoanEnd.substring (5, 7)) + "' AND yearAvailability = '" + Integer.parseInt(formatDateLoanEnd.substring (0, 4)) + "' AND uuidLoanRequester = '" + uuidUser + "' AND statut = '" + "NOT AVAILABLE" + "' WHERE UUID = '" + uuidEquipment.toString() + "'";


        try {
            statement.execute(setEquipmentNonAvailable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
    }

}
