package drop.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cliente_id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente")
    private List<Pedido> pedido = new ArrayList<>();

   /* @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente")
    private List<Comentario> comentario = new ArrayList<>();*/

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }



    public Cliente(String nome, String email, String endereco) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.toLowerCase();
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    /*
    *
    * Nesse exemplo, o método beforeSave() é anotado com @PrePersist e @PreUpdate. Ele será executado
    * antes de persistir uma nova entidade no banco de dados ou antes de atualizar uma entidade existente.
    * Dentro desse método, você verifica se o campo endereco não é nulo e, em seguida, o converte para minúsculas
    * usando o método toLowerCase(). Dessa forma, o valor do campo endereco será convertido
    * automaticamente para minúsculas antes de ser persistido ou atualizado no banco de dados.
    *
    * */
    @PrePersist
    @PreUpdate
    private void beforeSave() {
        if (endereco != null) {
            endereco = endereco.toLowerCase();
        }
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }


}
