package com.example.ZeePayUSSD.impl;

import com.example.ZeePayUSSD.constants.AllString;
import com.example.ZeePayUSSD.constants.Operations;
import com.example.ZeePayUSSD.constants.Pages;
import com.example.ZeePayUSSD.pojos.Menu;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.pojos.Zesa;
import com.example.ZeePayUSSD.repository.MenuRepository;
import com.example.ZeePayUSSD.repository.ZesaRepository;
import com.example.ZeePayUSSD.request.UssdRequest;
import com.example.ZeePayUSSD.response.UssdResponse;
import com.example.ZeePayUSSD.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class InitialMenuServiceImpl implements InitialMenuService {
@Autowired
MenuRepository menuRepository;
    @Autowired
    private Menu pojo_menu;
    @Autowired
    private MenuService menuService;
    @Autowired
    private Zesa zesa;
    @Autowired
    private ZesaService zesaService;
    @Autowired
    private GenericService genericService;
    @Autowired
    private ZesaRepository zesaRepository;
    @Autowired
   private  RegistrationSessionService registrationSessionService;
    ConcurrentHashMap<Integer, String> stages_assist = new ConcurrentHashMap<>();

    @Override
    public String getInitialMenuReg(UssdRequest request) {
        String sms = "";
        UssdResponse response = new UssdResponse();
        response.setSequence_number("rrrr");
        response.setVersion("rrrrr");
        response.setService_type(request.getService_type());
        response.setSource_addr(request.getSource_addr());
        response.setDest_addr(request.getDest_addr());
        response.setTimestamp(genericService.getCurrentDate());
        response.setCommand_status(request.getCommand_status());
        response.setData_coding(request.getData_coding());
        response.setMsg_len(request.getMsg_len());
        response.setMsg_content(AllString.menuUnregistered.getUrl());


        System.out.println("trying to print the xml response"+response);

        return genericService.responseToXml(response);
    }

    @Override
    public String getInitialMenuRegProgress(UssdRequest request)throws Exception {
        System.out.println("the debuggers got the issue at hand"+registrationSessionService.findStageByPhonenumber(request.getSource_addr()));
        UssdResponse response = new UssdResponse();
        String sms = "";
        response.setSequence_number("rrrr");
        response.setVersion("rePro");
        response.setService_type(request.getService_type());
        response.setSource_addr(request.getSource_addr());
        response.setDest_addr(request.getDest_addr());
        response.setTimestamp(genericService.getCurrentDate());
        response.setCommand_status(request.getCommand_status());
        response.setData_coding(request.getData_coding());
        response.setMsg_len(request.getMsg_len());
      //response.setMsg_content(AllString.menuUnregistered.getUrl());
        System.out.println("relaxed kkkkkkkkkkkk");

        if(request.getMsg_content().equals(Pages.Reg0.getUrl())){
            System.out.println("for unreg");
            genericService.reg_initial(request.getSource_addr(),"gg","gg");
            response.setMsg_content(AllString.menuUnregistered.getUrl());
        }
        else if(request.getMsg_content().equals(Pages.Reg1.getUrl()) && registrationSessionService.find_nested_page(request.getSource_addr()).equals("null")){
            System.out.println("for reg1");
         response.setMsg_content(AllString.entername.getUrl());//msg content method dynamic
            genericService.nextUSSDScreen(request.getSource_addr(),request.getMsg_content(), Operations.NAME.toString());
            System.out.println("hezvoko"+ Operations.NAME.toString());

         }else if(registrationSessionService.findStageByPhonenumber(request.getSource_addr()).equals("NAME")){
            System.out.println("save name");
            response.setMsg_content(AllString.entersurname.getUrl());//msg content method dynamic
            genericService.regSaveName(request.getSource_addr(),request.getMsg_content(),Operations.SURNAME.toString());
            //genericService.nextUSSDScreen(request.getSource_addr(),request.getMsg_content(), Operations.SURNAME.toString());


        }else if(registrationSessionService.findStageByPhonenumber(request.getSource_addr()).equals("SURNAME")){
            System.out.println("save surname");
            response.setMsg_content(AllString.enterdob.getUrl());
            genericService.regSaveSurname(request.getSource_addr(),request.getMsg_content(),Operations.DOB.toString());
        }else if(registrationSessionService.findStageByPhonenumber(request.getSource_addr()).equals("DOB")){
            System.out.println("save DOb");
            response.setMsg_content(AllString.enterIDNO.getUrl());
            genericService.regSaveDOB(request.getSource_addr(),request.getMsg_content(),Operations.IDNO.toString());
        }else if(registrationSessionService.findStageByPhonenumber(request.getSource_addr()).equals("IDNO")){
            System.out.println("save IDNO");
            genericService.regSaveIDNO(request.getSource_addr(),request.getMsg_content(),Operations.CONFIRM.toString());

           RegistrationSession c =  registrationSessionService.findAndConfirmRegDetails(request.getSource_addr());
           response.setMsg_content(AllString.ConfirmRegDetails.getUrl()+"name: "+c.getForename()+"\n"+"surname: "+ c.getSurname()+"\n"+"DOB: "+c.getDob()+"\n"+"IDNO :"+c.getId_no()+"\n"+"Press \n"+" 1. To Process \n"+"2. To Cancel\n"+"#. Back");

        }
        else if(registrationSessionService.findStageByPhonenumber(request.getSource_addr()).equals("CONFIRM") && registrationSessionService.find_nested_page(request.getSource_addr()).equals("2")){

            if(request.getMsg_content().equals("1")){
                System.out.println("Accepted Registration post To Registered");
                registrationSessionService.confirmeReg(request.getSource_addr());
                registrationSessionService.findAndPostTORegistered(request.getSource_addr());
                response.setMsg_content("Registration Successful");
            //}else if(regCancel.equals(request.getMsg_content()+"cancel")){
            }else if(request.getMsg_content().equals("2")){
                registrationSessionService.regCancel(request.getSource_addr());
                response.setMsg_content("Cancelled");
                System.out.println("registration cancelled");
            }else{
                response.setMsg_content("wrong input");
            }


        }

        else{
            response.setMsg_content("Request ERRR");
        }

        return genericService.responseToXml(response);
    }

    @Override
    public String getInitialMenuForRegistered(UssdRequest request) throws Exception{

        UssdResponse response = new UssdResponse();
        response.setSequence_number("rrrr");
        response.setVersion("rrrrr");
        response.setService_type(request.getService_type());
        response.setSource_addr(request.getSource_addr());
        response.setDest_addr(request.getDest_addr());
        response.setTimestamp(genericService.getCurrentDate());
        response.setCommand_status(request.getCommand_status());
        response.setData_coding(request.getData_coding());
        response.setMsg_len(request.getMsg_len());
       // response.setMsg_content(AllString.registered.getUrl());
        System.out.println("we are in the getInitialMenuForRegistered().......");
        System.out.println("this is the message content:"+request.getMsg_content());
     //   int menu = Integer.parseInt(request.getMsg_content());


  //  switch(menu){

  //          case 1:
                //for Bill Payment
                if(request.getMsg_content().equals("1") && menuService.find_menu(request.getSource_addr()).equals("billPayments") && menuService.find_nestedPage(request.getSource_addr()).equals("0")){
                    System.out.println("main if for Bill Payments ");
                        response.setMsg_content(AllString.zesa.getUrl());
                        pojo_menu.setSub_menu("zesa");
                        pojo_menu.setPhone_number(request.getSource_addr());
                        pojo_menu.setNested_page("1");
                        menuRepository.save(pojo_menu);

                } else if(request.getMsg_content().equals("1") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("1")){
                    response.setMsg_content(AllString.zesaChoice.getUrl());
                    pojo_menu.setNested_page("self");
                    pojo_menu.setLevel("0");
                    pojo_menu.setPhone_number(request.getSource_addr());
                    menuRepository.save(pojo_menu);
                }
                //-------------------------------------------------------------------
                else if(request.getMsg_content().equals("1") && menuService.find_levels(request.getSource_addr()).equals("0") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self")){
                    response.setMsg_content(AllString.zesa_self_menus[0]);
                    pojo_menu.setLevel("1");
                    pojo_menu.setNested_page("self1");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }
                //this is the condition for buying for others zesa token
                else if(request.getMsg_content().equals("2") && menuService.find_levels(request.getSource_addr()).equals("0") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self")){
                    //response.setMsg_content("* done it *");
                    response.setMsg_content(AllString.zesa_self_menus[0]);

                    zesa.setMeter_number(request.getMsg_content());
                    zesa.setAmount(request.getSource_addr());
                    zesaRepository.save(zesa);

                    //System.out.println("starting ZESA METER");
                    //zesaService.saveZesaMeter(request.getSource_addr(),request.getMsg_content());
                    //System.out.println("ending ZESA METER");
                    pojo_menu.setLevel("zesaother");
                    pojo_menu.setNested_page("self2");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }
                else if(menuService.find_levels(request.getSource_addr()).equals("zesaother") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self2")){
                    response.setMsg_content(AllString.zesa_self_menus[1]);
                    System.out.println("starting ZESA amount");
                    zesaService.saveZesaAmount(request.getSource_addr(),request.getMsg_content());
                    System.out.println("ending ZESA amount");
                    pojo_menu.setLevel("zesaother1");
                    pojo_menu.setNested_page("self3");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }
                else if(menuService.find_levels(request.getSource_addr()).equals("zesaother1") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self3")){
                    response.setMsg_content(AllString.zesa_self_menus[2]);
                    pojo_menu.setLevel("zesaother2");
                    pojo_menu.setNested_page("self4");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }else if(menuService.find_levels(request.getSource_addr()).equals("zesaother2")){
                    response.setMsg_content(AllString.zesa_self_menus[4]);
                    pojo_menu.setLevel("confirm_zesa_meta_details_forOthers");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }else if(menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details_forOthers")){
                    if(request.getMsg_content().equals("1") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details_forOthers")){
                        pojo_menu.setLevel("confirmed_zesa_buy_details_forOthers");
                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                        menuRepository.save(pojo_menu);
                        response.setMsg_content(AllString.zesa_self_menus[7]);
                    }else if(request.getMsg_content().equals("2") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details_forOthers")){
                        pojo_menu.setLevel("cancelled_zesa_buy_details_forOthers");
                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                        menuRepository.save(pojo_menu);
                        response.setMsg_content(AllString.zesa_self_menus[8]);
                    }else{
                        response.setMsg_content(AllString.zesa_self_menus[9]);
                        System.out.println("Else for buying others wrong input");
                    }
                }
                //  End  the conditions for buying for others zesa token
                else if(menuService.find_levels(request.getSource_addr()).equals("1") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self1")){
                    response.setMsg_content(AllString.zesa_self_menus[1]);
                    pojo_menu.setLevel("2");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }else if(menuService.find_levels(request.getSource_addr()).equals("2")){
                    response.setMsg_content(AllString.zesa_self_menus[4]);
                    pojo_menu.setLevel("confirm_zesa_meta_details");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                }else if(menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")){
                    //response.setMsg_content(AllString.zesa_self_menus[4]);
                    if(request.getMsg_content().equals("1") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")){
                        pojo_menu.setLevel("confirmed_zesa_buy_details");
                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                        menuRepository.save(pojo_menu);
                        response.setMsg_content(AllString.zesa_self_menus[7]);
                    }else if(request.getMsg_content().equals("2") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")){
                        pojo_menu.setLevel("cancelled_zesa_buy_details");
                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                        menuRepository.save(pojo_menu);
                        response.setMsg_content(AllString.zesa_self_menus[8]);
                    }else{
                        response.setMsg_content(AllString.zesa_self_menus[9]);
                    }
                }
                //--------------------------------------------------------------------------------

                    /**     //   else if(request.getMsg_content().equals("1") && menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self")){
                            else if(menuService.find_submenu(request.getSource_addr()).equals("zesa") && menuService.find_nestedPage(request.getSource_addr()).equals("self")){
                                if(menuService.find_levels(request.getSource_addr()).equals("0")) {
                                    response.setMsg_content(AllString.zesa_self_menus[0]);
                                    pojo_menu.setLevel("1");
                                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                                    menuRepository.save(pojo_menu);
                                }else if(menuService.find_levels(request.getSource_addr()).equals("1")){
                                        response.setMsg_content(AllString.zesa_self_menus[1]);
                                        pojo_menu.setLevel("2");
                                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                                        menuRepository.save(pojo_menu);

                                }else if(menuService.find_levels(request.getSource_addr()).equals("2")){
                                    response.setMsg_content(AllString.zesa_self_menus[4]);
                                    pojo_menu.setLevel("confirm_zesa_meta_details");
                                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                                    menuRepository.save(pojo_menu);
                                }
                                else if(menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")) {
                                    response.setMsg_content(AllString.zesa_self_menus[4]);
                               if(request.getMsg_content().equals("1") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")){
                                        pojo_menu.setLevel("confirmed_zesa_buy_details");
                                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                                        menuRepository.save(pojo_menu);
                                        response.setMsg_content(AllString.zesa_self_menus[7]);
                                    }else if(request.getMsg_content().equals("2") && menuService.find_levels(request.getSource_addr()).equals("confirm_zesa_meta_details")){
                                        pojo_menu.setLevel("cancelled_zesa_buy_details");
                                        pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                                        menuRepository.save(pojo_menu);
                                        response.setMsg_content(AllString.zesa_self_menus[8]);
                                    }else{
                                        response.setMsg_content(AllString.zesa_self_menus[9]);
                                    }


                                }else{

                                }

                            }*/
     else {
                    System.out.println("The case for Bill Payments");
                    response.setMsg_content(AllString.billPayments.getUrl());
                    pojo_menu.setMenu("billPayments");
                    pojo_menu.setPhone_number(request.getSource_addr());
                    pojo_menu.setNested_page("0");
                    menuRepository.save(pojo_menu);
                }
 /*              break;
         case 2:
                if(request.getMsg_content().equals("2") && menuService.find_menu(request.getSource_addr()).equals("billPayments") && menuService.find_nestedPage(request.getSource_addr()).equals("self")){
                    pojo_menu.setLevel("cancelled_zesa_buy_details");
                    pojo_menu.setPhone_number(request.getSource_addr());//kkkkk
                    menuRepository.save(pojo_menu);
                    response.setMsg_content(AllString.zesa_self_menus[8]);
                }else{
                    //for Airtime Purchase
                    response.setMsg_content(AllString.airtime.getUrl());

                }
                break;
            case 3:
                //for Securities Payments
                response.setMsg_content(AllString.security.getUrl());
                break;
            case 4:
                //Transfer Payments
                response.setMsg_content(AllString.transferes.getUrl());
                break;
            case 5:
                //Loan Payments
                response.setMsg_content(AllString.loan_payments.getUrl());
                break;
            case 6:
                //Wallet Services
                response.setMsg_content(AllString.wallet_service.getUrl());
                break;
            case 7:
                //My Profile
                break;

        }*/


        return genericService.responseToXml(response);
    }

    public   InitialMenuServiceImpl(){

    }

    public static String msgContent(String msg){
        return msg;
    }

}
