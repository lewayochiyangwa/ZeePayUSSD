package com.example.ZeePayUSSD.repository;

import com.example.ZeePayUSSD.pojos.Registered;
import com.example.ZeePayUSSD.pojos.RegistrationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredRepository extends JpaRepository<Registered, Integer> {
    Optional<Registered> findByPhone(String var1);

}
