package br.com.drop.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
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



}
