package com.github.marcelooo616.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table( name = "cliente" )
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome e obrigatorio.")
    private  String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo cpf e obrigatorio.")
    @CPF(message = "Informe um CPF valido")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;


}
