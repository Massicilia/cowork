package esgi.use_case;

public interface LoanRepository {


    void saveLoan(java.util.UUID UuidEquipment, java.util.UUID UuidClient,
            java.util.Date dateLoanBegin, java.util.Date dateLoanEnd);
    java.util.List<esgi.common.dto.LoanDto> getLoans();

}
