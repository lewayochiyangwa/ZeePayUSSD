package com.example.ZeePayUSSD.repository;

import com.example.ZeePayUSSD.pojos.RegistrationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RegistrationSessionRepository extends JpaRepository<RegistrationSession, Integer> {
    Optional<RegistrationSession> findByPhonenumber(String var1);
   // @Query(value="select top(1) current_page from registration_session  e where e.phonenumber = :keyword order by id desc",nativeQuery=true)
   String findStageByPhonenumber(@Param("keyword") String keyword);
   @Query(value="select * from registration_session  e where e.phonenumber =:keyword and status='complete' order by id desc",nativeQuery=true)
    void findAndPostTORegistered1(@Param("keyword") String keyword);
    @Query(value="select * from registration_session  e where e.phonenumber =:keyword  order by id desc",nativeQuery=true)
    RegistrationSession findAndConfirmRegDetails(String keyword);
    @Query(value="update registration_session  e set e.status='complete' where e.phonenumber =:keyword",nativeQuery=true)
    void confirmeReg(String keyword);
    @Query(value="select * from registration_session  e where e.phonenumber =:keyword  order by id desc",nativeQuery=true)
    String find_nested_page(String keyword);
    @Query(value="update registration_session  e set e.status='cancelled' where e.phonenumber =:keyword  order by id desc",nativeQuery=true)
    void regCancel(String keyword);



}
