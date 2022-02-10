package com.example.ZeePayUSSD.repository;

import com.example.ZeePayUSSD.pojos.Menu;
import com.example.ZeePayUSSD.pojos.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value="select * from menu  e where e.phone_number =:keyword  order by id desc",nativeQuery=true)
    Optional<Menu> findByPhone_number(String keyword);


}
