package br.com.drop.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_description", length = 265)
    private String description;

    @Column(name = "product_promotion_status")
    private boolean promotion_status;

    @Column(name = "product_stock")
    private Integer stock;

    @Column(name = "product_unit_price", precision = 20, scale = 2)
    private BigDecimal unit_price;

    @Column(name = "product_promotion_price", precision = 20, scale = 2)
    private BigDecimal promotion_price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Images> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rating> ratingList;


}
