package esgi.com.exposition;

import esgi.common.dto.EquipmentDto;
import esgi.infra.mysql.ClientRepositoryImpl;
import esgi.infra.mysql.EquipmentRepositoryImpl;
import esgi.use_case.LoanRegistration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@org.springframework.web.bind.annotation.CrossOrigin()
@org.springframework.web.bind.annotation.RestController
//@org.springframework.web.bind.annotation.RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST})
public class LoanController {

    //@org.springframework.web.bind.annotation.RequestMapping ("/")
    @org.springframework.web.bind.annotation.PostMapping("/loanrequest")
    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void LoanRegistration(String typeEquipment, String nameClient){


        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        LoanRegistration loan = new LoanRegistration(clientRepository.getUuidClientByNameAndSurname(nameClient), typeEquipment);
        loan.register();

    }

    @org.springframework.web.bind.annotation.RequestMapping ("/")
    public String home() {
        return "Spring boot is working!";
    }
}