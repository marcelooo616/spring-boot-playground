package br.com.drop.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id")
    @JsonIgnore
    private PersonalData personalData_data;

    @OneToOne(mappedBy = "user",  cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonIgnore
    private Address address;

    @OneToMany(mappedBy = "user_id", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Contact> contacts;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonalData getPersonalData_data() {
        return personalData_data;
    }

    public void setPersonalData_data(PersonalData personalData_data) {
        this.personalData_data = personalData_data;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
