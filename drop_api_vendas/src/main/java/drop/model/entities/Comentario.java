package drop.model.entities;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( name = "descricao", length = 255)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private Produto produto;

    public Comentario() {
    }

    public Comentario(String descricao, Cliente cliente) {
        this.descricao = descricao;
        this.cliente = cliente;
    }

    public Comentario(Integer id, String descricao, Cliente cliente) {
        this.id = id;
        this.descricao = descricao;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", descrica='" + descricao + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
