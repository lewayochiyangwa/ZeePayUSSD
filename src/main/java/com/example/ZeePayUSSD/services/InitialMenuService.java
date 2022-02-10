package com.example.ZeePayUSSD.services;

import com.example.ZeePayUSSD.request.UssdRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


public interface InitialMenuService {
    String getInitialMenuReg(UssdRequest var1);
    String getInitialMenuRegProgress(UssdRequest var1) throws Exception;
    Object getInitialMenuForRegistered(UssdRequest request) throws Exception;
}
