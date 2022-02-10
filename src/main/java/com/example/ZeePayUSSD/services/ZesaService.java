package com.example.ZeePayUSSD.services;


import com.example.ZeePayUSSD.pojos.Zesa;
import com.example.ZeePayUSSD.repository.ZesaRepository;
import com.example.ZeePayUSSD.request.UssdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZesaService {
    @Autowired
    private ZesaRepository zesaRepository;
    @Autowired
    private Zesa zesa;
    @Autowired
   private  UssdRequest ussdRequest;
    @Autowired
    public ZesaService(ZesaRepository zesaRepository, Zesa zesa, UssdRequest ussdRequest){
        this.zesaRepository = zesaRepository;
        this.zesa = zesa;
        this.ussdRequest = ussdRequest;
    }
    public String saveZesaAmount(String phone, String input) {
       zesa.setPhone(phone);
       zesa.setAmount(input);
       zesaRepository.save(zesa);
        //registerSessionRepository.save(registrationSession);
        System.out.println("we are now saving ZESA AMOUNT");
        return phone+" "+input;
    }
    public String saveZesaMeter(String phone, String input) {
        zesa.setPhone(phone);
        zesa.setMeter_number(input);
        zesaRepository.save(zesa);
        //registerSessionRepository.save(registrationSession);
        System.out.println("we are now saving ZESA METER");
        return phone+" "+input;
    }
}
