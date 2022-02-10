package com.example.ZeePayUSSD.services;

import com.example.ZeePayUSSD.pojos.Dao;
import com.example.ZeePayUSSD.pojos.Registered;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import com.example.ZeePayUSSD.repository.RegisteredRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;


public class RegisteredService {

    @Autowired
    private RegisteredRepository repo;

    public Registered findByPhone(Long id) {
        Optional<Registered> opt = repo.findById(Math.toIntExact(id));
        Registered reg = null;
        if(opt.isPresent()){
            reg = opt.get();
        }else{
            opt.orElseThrow();
        }
        return reg;
        // return repo.getOne(id);
    }


}
