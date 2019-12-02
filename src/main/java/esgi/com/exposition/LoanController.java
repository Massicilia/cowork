package esgi.com.exposition;

import esgi.use_case.LoanRegistration;
import esgi.infra.mysql.UserRepositoryImpl;
import esgi.common.dto.LoanRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;



@org.springframework.web.bind.annotation.CrossOrigin()
@RestController
@RequestMapping(value = "/", method = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.DELETE, org.springframework.web.bind.annotation.RequestMethod.PUT, org.springframework.web.bind.annotation.RequestMethod.POST})
//@org.springframework.web.bind.annotation.CrossOrigin (allowCredentials = "true",methods = {org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST, org.springframework.web.bind.annotation.RequestMethod.PUT})
//@org.springframework.web.bind.annotation.RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST})
public class LoanController {
    // 1 request
    Logger logger = LoggerFactory.getLogger(esgi.com.exposition.LoanController.class);

    //@org.springframework.web.bind.annotation.RequestMapping ("/")
    //@org.springframework.web.bind.annotation.CrossOrigin("http://localhost:4200/")
    @PostMapping ("/loanrequest")
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public void LoanRegistration(@RequestBody LoanRequestDto loanRequestDto, javax.servlet.http.HttpServletResponse response){
        //response.addHeader ("Access-Control-Allow-Origin", "http://localhost:4200/");
        UserRepositoryImpl userRepository = new UserRepositoryImpl ();
        LoanRegistration loan = new LoanRegistration(userRepository.getUuidUserByNameAndSurname(loanRequestDto.getNameUser (), loanRequestDto.getSurnameUser ()), loanRequestDto.getTypeEquipment (), loanRequestDto.getDateLoanBegin (), loanRequestDto.getDateLoanEnd () );
        loan.register();
    }

    @RequestMapping ("/")
    @org.springframework.web.bind.annotation.ResponseBody
    public String home(javax.servlet.http.HttpServletResponse response) {
        //response.addHeader ("Access-Control-Allow-Origin", "http://localhost:4200/");
        return "Spring boot is working!";
    }
}