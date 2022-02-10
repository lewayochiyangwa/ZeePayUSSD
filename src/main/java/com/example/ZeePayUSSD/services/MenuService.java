package com.example.ZeePayUSSD.services;

import com.example.ZeePayUSSD.pojos.Menu;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    MenuRepository repo;



    public String find_menu(String keyword)throws Exception{
        String menu="";
        Optional<Menu> opt = repo.findByPhone_number(keyword);
        Menu reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            menu = reg.getMenu();

        }else{
           // opt.orElseThrow();
        }
        System.out.println("this is the nested"+menu);
        return menu;

    }

    public String find_submenu(String keyword)throws Exception{
        String sub_menu="";
        Optional<Menu> opt = repo.findByPhone_number(keyword);
        Menu reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            sub_menu = reg.getSub_menu();

        }else{
            // opt.orElseThrow();
        }
        System.out.println("this is the find_submenu().......:"+sub_menu);
        return sub_menu;

    }

    public String find_nestedPage(String keyword)throws Exception{
        String sub_menu="";
        Optional<Menu> opt = repo.findByPhone_number(keyword);
        Menu reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            sub_menu = reg.getNested_page();

        }else{
            // opt.orElseThrow();
        }
        System.out.println("this is the find_submenu().......:"+sub_menu);
        return sub_menu;

    }

    public String find_levels(String keyword)throws Exception{
        String level="";
        Optional<Menu> opt = repo.findByPhone_number(keyword);
        Menu reg = null;
        if(opt.isPresent()){
            reg = opt.get();
            level = reg.getLevel();

        }else{
            // opt.orElseThrow();
        }
        System.out.println("this is the find_submenu().......:"+level);
        return level;

    }
}
