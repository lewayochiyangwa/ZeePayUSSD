package com.example.ZeePayUSSD.impl;

import com.example.ZeePayUSSD.constants.AllString;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.RegistrationSessionRepository;
import com.example.ZeePayUSSD.request.UssdRequest;
import com.example.ZeePayUSSD.response.UssdResponse;
import com.example.ZeePayUSSD.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Service
@Transactional
public class GenericServiceImpl implements GenericService {
    @Autowired
    private RegistrationSessionRepository registerSessionRepository;
    @Autowired
    public RegistrationSession registrationSession;

    @Autowired
    public GenericServiceImpl(RegistrationSessionRepository registerSessionRepository,RegistrationSession registrationSession) {
        this.registerSessionRepository = registerSessionRepository;
        this.registrationSession = registrationSession;
    }

    public GenericServiceImpl() {
    }

    @Override
    public String getPage() {
        return null;
    }

    @Override
    public String nextUSSDScreen(String Phone, String input, String Operation) {
        ConcurrentHashMap<String,String> details = new ConcurrentHashMap<>();
        registrationSession.setPhonenumber(Phone);
        System.out.println("takubata chokwadi"+Operation);
        registrationSession.setCurrent_page(Operation.toString());
        registerSessionRepository.save(registrationSession);
        details.put(Phone,Operation);

        //registerSessionRepository.save(details);
        return details.toString();
    }

    @Override
    public String reg_initial(String phone, String input, String operation) {
        registrationSession.setPhonenumber(phone);
       // registrationSession.setDob(input);
        registrationSession.setCurrent_page("null");
        registrationSession.setNested_page("null");
        registerSessionRepository.save(registrationSession);
        System.out.println("this is the data passed for Surname:"+phone+" "+input+" "+operation);
        return phone+" "+input+" "+operation;
    }


    @Override
    public String regSaveName(String phone, String input, String operation) {
        registrationSession.setPhonenumber(phone);
        registrationSession.setForename(input);
        registrationSession.setCurrent_page(operation.toString());
        registerSessionRepository.save(registrationSession);
        System.out.println("this is the data passed:"+phone+" "+input+" "+operation);
        return phone+" "+input+" "+operation;
    }

    @Override
    public String regSaveSurname(String phone, String input, String operation) {
        registrationSession.setPhonenumber(phone);
        registrationSession.setSurname(input);
        registrationSession.setCurrent_page(operation.toString());
        registerSessionRepository.save(registrationSession);
        System.out.println("this is the data passed for Surname:"+phone+" "+input+" "+operation);
        return phone+" "+input+" "+operation;
    }
    @Override
    public String regSaveDOB(String phone, String input, String operation) {
        registrationSession.setPhonenumber(phone);
        registrationSession.setDob(input);
        registrationSession.setCurrent_page(operation.toString());
        registerSessionRepository.save(registrationSession);
        System.out.println("this is the data passed for Surname:"+phone+" "+input+" "+operation);
        return phone+" "+input+" "+operation;
    }
    @Override
    public String regSaveIDNO(String phone, String input, String operation) {
        registrationSession.setPhonenumber(phone);
        registrationSession.setId_no(input);
        registrationSession.setCurrent_page(operation.toString());
        registrationSession.setStatus("complete");
        registrationSession.setNested_page("2");
        registerSessionRepository.save(registrationSession);
        System.out.println("this is the data passed for Surname:"+phone+" "+input+" "+operation);
        return phone+" "+input+" "+operation;
    }

    @Override
    public String genericResponse(UssdRequest var1, String var2) {
        return null;
    }

    @Override
    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }



    @Override
    public String responseToXml(UssdResponse response) {
        StringWriter sw = new StringWriter();
        JAXB.marshal(response, sw);
        return sw.toString();
    }

    @Override
    public Boolean inputIsNumber(String var1) {
        return null;
    }

    @Override
    public String finalResponse(UssdRequest var1, String var2) {
        return null;
    }

    @Override
    public Boolean inputIsDouble(String var1) {
        return null;
    }

    @Override
    public String isIdExists(UssdRequest var1, String var2, String var3) {
        return null;
    }

    @Override
    public Optional<RegistrationSession> loggedSubscriber(UssdRequest request) {
        return registerSessionRepository.findByPhonenumber(request.getSource_addr());
    }
    public UssdRequest getObjectFromXml(String requestBody) {
        return (UssdRequest) JAXB.unmarshal(new StringReader(requestBody), UssdRequest.class);
    }
       public UssdResponse genericRegResponse(UssdRequest request,String sms_response){
           UssdResponse response = new UssdResponse();
           response.setSequence_number("rrrr");
           response.setVersion("rePro");
           response.setService_type(request.getService_type());
           response.setSource_addr(request.getSource_addr());
           response.setDest_addr(request.getDest_addr());
           response.setTimestamp(getCurrentDate());
           response.setCommand_status(request.getCommand_status());
           response.setData_coding(request.getData_coding());
           response.setMsg_len(request.getMsg_len());
           response.setMsg_content(sms_response);

           return response;
       }

}
