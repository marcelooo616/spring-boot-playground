package drop.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id")
    private Integer id;

    @Column(name = "descricao",length = 100)
    private String descricao;

    @Column(name = "valor_Unitario", precision = 20, scale = 2)
    private BigDecimal valor_unitario;

    @Column(name = "url_image")
    private String ulr_image;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "estoque")
    private Integer estoque;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("produto")
    private List<Comentario> comentarios = new ArrayList<>();



    public Produto() {
    }

    public Produto(String descricao, BigDecimal valor_unitario, String ulr_image, String categoria, Integer estoque) {
        this.descricao = descricao;
        this.valor_unitario = valor_unitario;
        this.ulr_image = ulr_image;
        this.categoria = categoria;
        this.estoque = estoque;
    }

    public Produto(Integer id, String descricao, BigDecimal valor_unitario, String ulr_image, Integer estoque) {
        this.id = id;
        this.descricao = descricao;
        this.valor_unitario = valor_unitario;
        this.ulr_image = ulr_image;
        this.categoria = categoria;
        this.estoque = estoque;
    }


   /* public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }*/

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

    public BigDecimal getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(BigDecimal valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public String getUlr_image() {
        return ulr_image;
    }

    public void setUlr_image(String ulr_image) {
        this.ulr_image = ulr_image;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    @PrePersist
    @PreUpdate
    private void beforeSave() {
        if (categoria != null) {
            categoria = categoria.toLowerCase();
        }
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor_unitario=" + valor_unitario +
                ", ulr_image='" + ulr_image + '\'' +
                ", categoria='" + categoria + '\'' +
                ", estoque=" + estoque +
                '}';
    }
}
