package com.example.ZeePayUSSD.services;




import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.RegistrationSessionRepository;
import com.example.ZeePayUSSD.request.UssdRequest;
import com.example.ZeePayUSSD.response.UssdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface GenericService {
  //  @Autowired
  //  public RegistrationSessionRepository repo;
  String getPage();
    String nextUSSDScreen(String Phone,String input, String Operation);
    String reg_initial(String phone, String input, String operation);
    String regSaveName(String Phone,String input, String Operation);
    String regSaveSurname(String Phone,String input, String Operation);
    String regSaveDOB(String Phone,String input, String Operation);
    String regSaveIDNO(String phone, String input, String operation);
    String genericResponse(UssdRequest var1, String var2);
    String getCurrentDate();
    public UssdRequest getObjectFromXml(String var1);
    String responseToXml(UssdResponse var1);
   Boolean inputIsNumber(String var1);
   String finalResponse(UssdRequest var1, String var2);
   Boolean inputIsDouble(String var1);
   String isIdExists(UssdRequest var1, String var2, String var3);
    Optional<RegistrationSession>  loggedSubscriber(UssdRequest var1);

  // SubscriberSession getSubscriberSession(UssdRequest var1);

  // SubscriberSession loggedSubscriber(UssdRequest var1);

  // RegisterSession getRegisterSession(UssdRequest var1);



 //  Boolean isDateValid(String var1);

  // String getCdsNumber(UssdRequest var1);

 //  String invalidInput(UssdRequest var1);



 //  List<BankNext> getBanksNext(UssdRequest var1, String var2);



 //  List<Custodian> getCustodianList(UssdRequest var1);

 //  List<BankNext> getBankList(UssdRequest var1);



  // Date getActualDate();

 //  List<String> getMyTitles();

 //  List<String> getGender();

 //  List<Broker> getBrokersList(UssdRequest var1);

  // List<String> getMyCountries();

 //  List<CompanySecurities> getCompanySecuritiesList(UssdRequest var1);

 //  List<CompanyPrices> getCompanySecuritiesPrice(UssdRequest var1);

 //  List<MarketWatch> getBuys(UssdRequest var1);

 //  List<MarketWatch> getSells(UssdRequest var1);

 //  List<Counter> getAllCompaniesZse(UssdRequest var1, String var2, String var3);

//   List<Counter> getAllCompaniesNext(UssdRequest var1, String var2);

//   TradeSession getTradeSession(UssdRequest var1);

 //  String sessionId();

   //  List<MyOrders> getMyOrdersNext(UssdRequest var1, String var2, String var3);

   // Integer getSessionId();
}
