package esgi.use_case;

import esgi.use_case.ClientRepository;
import esgi.use_case.LoanRepository;
import esgi.common.dto.ClientFullDto;
import esgi.common.exceptions.ClientNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;

public interface LoanRepository {


    void saveLoan(java.util.UUID uuidLoan, java.util.UUID UuidEquipment, java.util.UUID UuidClient);

}
