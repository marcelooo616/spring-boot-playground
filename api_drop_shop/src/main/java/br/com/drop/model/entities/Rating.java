package br.com.drop.model.entities;



import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rating_id")
    private Integer id;

    @Column(name = "rating_product_note")
    @DecimalMin(value = "0", inclusive = true, message = "The minimum rating is 0")
    @DecimalMax(value = "10", inclusive = true, message = "The maximum rating is 10")
    private BigDecimal product_note;

    @Column(name = "rating_review")
    private String review;

    @Column(name = "rating_evaluation_date")
    private LocalDate evaluation_date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getProduct_note() {
        return product_note;
    }

    public void setProduct_note(BigDecimal product_note) {
        this.product_note = product_note;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDate getEvaluation_date() {
        return evaluation_date;
    }

    public void setEvaluation_date(LocalDate evaluation_date) {
        this.evaluation_date = evaluation_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @PrePersist
    public void prePersist() {
        evaluation_date = LocalDate.now();
    }
}
