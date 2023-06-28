package br.com.drop.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_residential_number")
    private String residential_number;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_district")
    private String district;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_state")
    private String state;

    @Column(name = "address_cep")
    private String cep;

    @Column(name = "address_nation")
    private String nation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
