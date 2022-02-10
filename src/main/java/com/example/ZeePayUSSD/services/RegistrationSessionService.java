package com.example.ZeePayUSSD.services;

import com.example.ZeePayUSSD.pojos.Dao;
import com.example.ZeePayUSSD.pojos.Registered;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.RegisteredRepository;
import com.example.ZeePayUSSD.repository.RegistrationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;
import java.util.Optional;
@Service
public class RegistrationSessionService {
    @Autowired
    private RegisteredRepository registeredRepository;
    @Autowired
    private Registered registered;
    @Autowired
    private RegistrationSessionRepository repo;
    @Autowired
    private RegistrationSession registrationSession;

    public RegistrationSession findByPhonenumber(Long id) {
        Optional<RegistrationSession> opt = repo.findById(Math.toIntExact(id));
        RegistrationSession reg = null;
        if(opt.isPresent()){
            reg = opt.get();
        }else{
            opt.orElseThrow();
        }
        return reg;
        // return repo.getOne(id);
    }

    public String findStageByPhonenumber(String keyword)throws Exception{
        Dao dao = new Dao();
        String currentpage="";
        try (Connection con = dao.getConnection(); Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery("select top(1) current_page from registration_session  e where e.phonenumber ="+"'"+keyword+"'"+" order by id desc");
                while (rs.next()) {
                    currentpage=   rs.getString("current_page");
                    System.out.print("Get Current Page:" +rs.getString("current_page") + "-");

                }
        return currentpage;
    }
    }

    public void findAndPostTORegistered(String keyword)throws Exception{
        Dao dao = new Dao();
        Connection con2 = dao.getConnection();

        Optional<RegistrationSession> opt = repo.findByPhonenumber(keyword);
        RegistrationSession reg = null;
        if(opt.isPresent()){
          //  opt.getSurname
            reg = opt.get();
            Statement st = con2.createStatement();
            String query = "insert into Registered (dob,forename,id_no,surname,phone) values ("+"'"+reg.getDob()+"'"+","+"'"+reg.getForename()+"'"+","+"'"+reg.getId_no()+"'"+","+"'"+ reg.getSurname()+"'"+","+"'"+  reg.getPhonenumber()+"'"+")";
            //String query = "insert into Registered (dob,forename,id_no,surname,phone) values ("+"'"+rs.getString("dob")+"'"+","+"'"+rs.getString("forename")+"'"+","+"'"+rs.getString("surname")+"'"+","+"'"+rs.getString("id_no")+"'"+","+"'"+ rs.getString("phonenumber")+"'"+")";
            st.executeUpdate(query);
            con2.close();
           System.out.println("unonyeba iwe");

        }else{
            opt.orElseThrow();
        }
       // return amortization;

    }


    public RegistrationSession findAndConfirmRegDetails(String keyword)throws Exception{
        Dao dao = new Dao();
        Connection con2 = dao.getConnection();

        Optional<RegistrationSession> opt = repo.findByPhonenumber(keyword);
        RegistrationSession reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            reg.getForename();
            reg.getSurname();
            reg.getDob();
            reg.getId_no();

            System.out.println("find And ConfirmRegDetails Method if present");

        }else{
            System.out.println("find And ConfirmRegDetails Method for not  present");
            opt.orElseThrow();
        }
         return reg;

    }

   public  void confirmeReg(String keyword){
        Optional<RegistrationSession> opt = repo.findByPhonenumber(keyword);
        System.out.println("update for complete");

    }
    public String find_nested_page(String keyword)throws Exception{
        String nested_page="";
        Optional<RegistrationSession> opt = repo.findByPhonenumber(keyword);
        RegistrationSession reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            nested_page = reg.getNested_page();

        }else{
            opt.orElseThrow();
        }
        System.out.println("this is the nested"+nested_page);
         return nested_page;

    }
    public void regCancel(String keyword)throws Exception{
        Dao dao = new Dao();
        Connection con2 = dao.getConnection();
        Statement stmt = con2.createStatement();
        String nested_page="";
        Optional<RegistrationSession> opt = repo.findByPhonenumber(keyword);
        String sql = "UPDATE registration_session SET status = 'cancelled' WHERE phonenumber="+"'"+keyword+"'";
        stmt.executeUpdate(sql);
        System.out.println("This is the regCancel() Method for cancelling Registrations "+nested_page);

    }
}
