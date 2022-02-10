package com.example.ZeePayUSSD;

import com.example.ZeePayUSSD.pojos.Dao;
import com.example.ZeePayUSSD.pojos.Registered;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@SpringBootApplication
public class ZeePayUssdApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ZeePayUssdApplication.class, args);
      if(1==1){
		System.out.println("one is eqiual");
	  }else if(2==2){
		  System.out.println("two is eqiual");
	  }else if(3==3){
		  System.out.println("three is eqiual");
	  }else{
		  System.out.println("kkkkk");
	  }

	}

}
