package br.com.drop.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion_status() {
        return promotion_status;
    }

    public void setPromotion_status(boolean promotion_status) {
        this.promotion_status = promotion_status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(BigDecimal promotion_price) {
        this.promotion_price = promotion_price;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
