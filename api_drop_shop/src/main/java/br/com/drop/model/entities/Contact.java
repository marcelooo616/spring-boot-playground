package br.com.drop.model.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Column(name = "contact_type")
    private String type;

    @Column(name = "contact_value")
    private String value;

    @Column(name = "contact_preferential")
    private boolean preferential;

    @Column(name = "contact_observation")
    private String observation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPreferential() {
        return preferential;
    }

    public void setPreferential(boolean preferential) {
        this.preferential = preferential;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
