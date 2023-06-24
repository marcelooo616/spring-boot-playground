package br.com.drop.model.entities;


import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "personal_data")
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personal_data_id")
    private Integer id;

    @Column(name = "personal_data_completed_name")
    private String completed_name;

    @Column(name = "personal_data_birthday")
    private String birthday;

    @Column(name = "personal_data_gender")
    private String gender;

    @Column(name = "personal_data_cpf")
    private String cpf;

    @Column(name = "personal_data_whatsapp")
    private String whatsapp;

    @Column(name = "personal_data_email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    //@JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompleted_name() {
        return completed_name;
    }

    public void setCompleted_name(String completed_name) {
        this.completed_name = completed_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
