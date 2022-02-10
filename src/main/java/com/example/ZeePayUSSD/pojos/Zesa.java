package com.example.ZeePayUSSD.pojos;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(
        name = "Zesa"
)
@Component
public class Zesa {

    public  Zesa(){}
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "meter_number"
    )
    private String  meter_number;

    public Zesa(String meter_number, String amount) {
        this.meter_number = meter_number;
        this.amount = amount;
    }

    @Column(
            name = "amount"
    )
    private String  amount;
    @Column(
            name = "phone"
    )
    private String  phone;
    @Column(
            name = "trans_time"
    )
    private String  trans_time;

    @Override
    public String toString() {
        return "Zesa{" +
                "id=" + id +
                ", meter_number='" + meter_number + '\'' +
                ", amount='" + amount + '\'' +
                ", phone='" + phone + '\'' +
                ", trans_time='" + trans_time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zesa zesa = (Zesa) o;
        return Objects.equals(id, zesa.id) && Objects.equals(meter_number, zesa.meter_number) && Objects.equals(amount, zesa.amount) && Objects.equals(phone, zesa.phone) && Objects.equals(trans_time, zesa.trans_time) && Objects.equals(status, zesa.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meter_number, amount, phone, trans_time, status);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(String meter_number) {
        this.meter_number = meter_number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTrans_time() {
        return trans_time;
    }

    public void setTrans_time(String trans_time) {
        this.trans_time = trans_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Zesa(Long id, String meter_number, String amount, String phone, String trans_time, String status) {
        this.id = id;
        this.meter_number = meter_number;
        this.amount = amount;
        this.phone = phone;
        this.trans_time = trans_time;
        this.status = status;
    }

    @Column(
            name = "status"
    )
    private String  status;

}
