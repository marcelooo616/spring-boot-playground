package br.com.drop.model.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
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


}
