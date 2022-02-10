package com.example.ZeePayUSSD.repository;


import com.example.ZeePayUSSD.pojos.Zesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZesaRepository extends JpaRepository<Zesa, Integer> {


}
