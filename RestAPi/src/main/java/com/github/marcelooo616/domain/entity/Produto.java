package com.github.marcelooo616.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "Campo descrição e obrigatorio.")
    private String descricao;

    @Column(name = "preco_unitario", precision = 20, scale = 2)
    @NotNull(message = "Campo preço e obrigatorio.")
    private BigDecimal preco;




}
