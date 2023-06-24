package br.com.drop.model.entities;


import javax.persistence.*;

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




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getResidential_number() {
        return residential_number;
    }

    public void setResidential_number(String residential_number) {
        this.residential_number = residential_number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
