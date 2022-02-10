package com.example.ZeePayUSSD.pojos;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(
        name = "registration_session"
)
@Component
public class RegistrationSession {

    public RegistrationSession() {
    }

    public RegistrationSession(String forename, String surname, String dob, String id_no) {
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.id_no = id_no;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

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

    public String getNested_page() {
        return nested_page;
    }

    @Override
    public String toString() {
        return "RegistrationSession{" +
                "id=" + id +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", nested_page='" + nested_page + '\'' +
                ", dob='" + dob + '\'' +
                ", status='" + status + '\'' +
                ", id_no='" + id_no + '\'' +
                ", current_page='" + current_page + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationSession that = (RegistrationSession) o;
        return Objects.equals(id, that.id) && Objects.equals(forename, that.forename) && Objects.equals(surname, that.surname) && Objects.equals(nested_page, that.nested_page) && Objects.equals(dob, that.dob) && Objects.equals(status, that.status) && Objects.equals(id_no, that.id_no) && Objects.equals(current_page, that.current_page) && Objects.equals(phonenumber, that.phonenumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forename, surname, nested_page, dob, status, id_no, current_page, phonenumber);
    }

    public void setNested_page(String nested_page) {
        this.nested_page = nested_page;
    }

    public RegistrationSession(Long id, String forename, String surname, String nested_page, String dob, String status, String id_no, String current_page, String phonenumber) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.nested_page = nested_page;
        this.dob = dob;
        this.status = status;
        this.id_no = id_no;
        this.current_page = current_page;
        this.phonenumber = phonenumber;
    }

    @Column(
            name = "nestedpage"
    )
    private String nested_page;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrent_page() {
        return current_page;
    }

    @Column(
            name = "dob"
    )
    private String dob;

    public RegistrationSession(Long id, String forename, String surname, String dob, String status, String id_no, String current_page, String phonenumber) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.status = status;
        this.id_no = id_no;
        this.current_page = current_page;
        this.phonenumber = phonenumber;
    }

    @Column(
            name = "status"
    )
    private String status;

    public String getCurrent_page(String s) {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    @Column(
            name = "id_no"
    )
    private String id_no;

    public RegistrationSession(Long id, String forename, String surname, String dob, String id_no, String current_page, String phonenumber) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.id_no = id_no;
        this.current_page = current_page;
        this.phonenumber = phonenumber;
    }

    @Column(
            name = "current_page"
    )
    private String current_page;

    public RegistrationSession(Long id, String forename, String surname, String dob, String id_no, String phonenumber) {
        this.id = id;
        this.forename = forename;
        this.surname = surname;
        this.dob = dob;
        this.id_no = id_no;
        this.phonenumber = phonenumber;
    }

    @Column(
            name = "phonenumber"
    )
    private String phonenumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
