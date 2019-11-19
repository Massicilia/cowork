package esgi.use_case;

import esgi.common.dto.LoanDto;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

public interface LoanRepository {


    void saveLoan(UUID UuidEquipment, UUID UuidClient,
                  LocalDate dateLoanBegin, LocalDate dateLoanEnd);
    List<LoanDto> getLoans();

}
