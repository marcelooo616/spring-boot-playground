package com.github.marcelooo616.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id")
    private Integer id;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "preco_unitario", precision = 20, scale = 2)
    private BigDecimal preco;




}
