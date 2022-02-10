package com.example.ZeePayUSSD.request;


import com.example.ZeePayUSSD.constants.AllString;
import com.example.ZeePayUSSD.constants.Pages;
import com.example.ZeePayUSSD.pojos.Registered;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.RegisteredRepository;
import com.example.ZeePayUSSD.repository.RegistrationSessionRepository;
import com.example.ZeePayUSSD.response.UssdResponse;
import com.example.ZeePayUSSD.services.GenericService;
import com.example.ZeePayUSSD.services.InitialMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/v1/zeepay/ussd/"})
public class UssdRequestController {

   @Autowired
public GenericService genericService;
    @Autowired
public InitialMenuService initialMenuService;
    @Autowired
public UssdRequest ussdRequest;
    @Autowired
    public RegistrationSessionRepository registrationSessionRepository ;
    @Autowired
    public Registered registered;
    @Autowired
    public RegisteredRepository registeredRepository;

    public UssdRequestController() {
    }

    @Autowired
    public UssdRequestController(GenericService genericService, InitialMenuService initialMenuService,
                                 UssdRequest ussdRequest,Registered registered,RegisteredRepository registeredRepository){
        this.genericService = genericService;
        this.initialMenuService = initialMenuService;
        this.ussdRequest = ussdRequest;
        this.registered = registered;
        this.registeredRepository=registeredRepository;

    }
    @RequestMapping(path = {"/initial/menu"}, consumes = {"application/xml", "*/*"}, produces = {"application/xml", "*/*"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public StringBuffer initialMenu(@RequestBody String requestBody)throws Exception {
        StringBuffer returned = new StringBuffer();
        StringBuffer s =returned;
        //String s ="";
        UssdResponse response = new UssdResponse();



        UssdRequest request = this.genericService.getObjectFromXml(requestBody);
        String message = request.getMsg_content();

       // System.out.println("llllll"+registrationSessionRepository.findByPhonenumber(request.getSource_addr()));

      //  Optional<RegistrationSession> registrationSession  = registrationSessionRepository.findByPhonenumber(request.getSource_addr().toString());
        Optional<Registered> registered = registeredRepository.findByPhone(request.getSource_addr().toString());


        if(registered.isPresent()){
            if(request.getMsg_content().equals("0")){
                ///UssdResponse response = new UssdResponse();
                response.setSequence_number("rrrr");
                response.setVersion("rrrrr");
                response.setService_type(request.getService_type());
                response.setSource_addr(request.getSource_addr());
                response.setDest_addr(request.getDest_addr());
                response.setTimestamp(genericService.getCurrentDate());
                response.setCommand_status(request.getCommand_status());
                response.setData_coding(request.getData_coding());
                response.setMsg_len(request.getMsg_len());
                response.setMsg_content(AllString.registered.getUrl());

                s =  returned.append(genericService.responseToXml(response));

            }else{
                System.out.println("We are now calling from Registered its  not empty"+ request.getSource_addr());
                s =  returned.append(initialMenuService.getInitialMenuForRegistered(request));
            }

        }else{
            System.out.println("its   now registering process");
              s =  returned.append(initialMenuService.getInitialMenuRegProgress(request));
        }

        return s;
      //  return genericService.responseToXml(response);

    }
}
