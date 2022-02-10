package com.example.ZeePayUSSD.pojos;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(
        name = "registered"
)
@Component
public class Registered {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "forename"
    )
    private String  forename;
    @Column(
            name = "surname"
    )
    private String surname;
    @Column(
            name = "dob"
    )
    private String dob;

    @Column(
            name = "phone"
    )
    private String phone;
    @Column(
            name = "current_page"
    )
    private String current_page;
    @Column(
            name = "id_no"
    )
    private String id_no;
    @Column(
            name = "status"
    )
    private String status;
}
