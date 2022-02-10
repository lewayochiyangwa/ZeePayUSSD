package com.example.ZeePayUSSD.pojos;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(
        name = "Menu"
)
@Component
public class Menu {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "menu"
    )
    private String  menu;

    @Column(
            name = "sub_menu"
    )
    private String  sub_menu;

    @Column(
            name = "phone_number"
    )
    private String  phone_number;

    @Column(
            name = "level"
    )
    private String  level;

    public String getLevel() {
        return level;
    }
public Menu(String level,String phone_number){
        this.level=level;
    this.phone_number=phone_number;
}
    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menu='" + menu + '\'' +
                ", sub_menu='" + sub_menu + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", level='" + level + '\'' +
                ", nested_page='" + nested_page + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu1 = (Menu) o;
        return Objects.equals(id, menu1.id) && Objects.equals(menu, menu1.menu) && Objects.equals(sub_menu, menu1.sub_menu) && Objects.equals(phone_number, menu1.phone_number) && Objects.equals(level, menu1.level) && Objects.equals(nested_page, menu1.nested_page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menu, sub_menu, phone_number, level, nested_page);
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Menu(Long id, String menu, String sub_menu, String phone_number, String level, String nested_page) {
        this.id = id;
        this.menu = menu;
        this.sub_menu = sub_menu;
        this.phone_number = phone_number;
        this.level = level;
        this.nested_page = nested_page;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSub_menu() {
        return sub_menu;
    }

    public void setSub_menu(String sub_menu) {
        this.sub_menu = sub_menu;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNested_page() {
        return nested_page;
    }

    public void setNested_page(String nested_page) {
        this.nested_page = nested_page;
    }

    public Menu(Long id, String menu, String sub_menu, String phone_number, String nested_page) {
        this.id = id;
        this.menu = menu;
        this.sub_menu = sub_menu;
        this.phone_number = phone_number;
        this.nested_page = nested_page;
    }

    @Column(
            name = "nested_page"
    )
    private String  nested_page;



   public Menu(){}








}
