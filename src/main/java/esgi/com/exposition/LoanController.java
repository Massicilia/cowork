package esgi.com.exposition;

import esgi.use_case.LoanRegistration;

//@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController
//@org.springframework.web.bind.annotation.RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST})
public class LoanController {

    //@org.springframework.web.bind.annotation.RequestMapping ("/")
    @org.springframework.web.bind.annotation.PostMapping("/loanrequest")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void LoanRegistration(String typeEquipment, String nameUser,
                                 java.util.Date dateLoanBegin, java.util.Date dateLoanEnd){


        esgi.infra.mysql.UserRepositoryImpl userRepository = new esgi.infra.mysql.UserRepositoryImpl ();
        LoanRegistration loan = new LoanRegistration(userRepository.getUuidUserByNameAndSurname(nameUser), typeEquipment, dateLoanBegin, dateLoanEnd );
        loan.register();
    }

    @org.springframework.web.bind.annotation.RequestMapping ("/")
    public String home() {
        return "Spring boot is working!";
    }
}