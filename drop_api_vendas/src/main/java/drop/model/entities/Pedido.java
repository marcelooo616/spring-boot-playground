package drop.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total_pedido", precision = 20, scale = 2)
    private BigDecimal total_pedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("pedido")
    private List<Item> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Cliente cliente, LocalDate dataPedido, BigDecimal total_pedido, List<Item> itens) {
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total_pedido = total_pedido;
        this.itens = itens;
    }

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedido, BigDecimal total_pedido, List<Item> itens) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total_pedido = total_pedido;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotal_pedido() {
        return total_pedido;
    }

    public void setTotal_pedido(BigDecimal total_pedido) {
        this.total_pedido = total_pedido;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido{id=").append(id)
                .append(", cliente=").append(cliente)
                .append(", dataPedido=").append(dataPedido)
                .append(", total_pedido=").append(total_pedido)
                .append(", pedidos=[");

        if (itens != null) {
            for (Item item : itens) {
                builder.append(item.toString()).append(", ");
            }
            // Remova a última vírgula e espaço
            if (!itens.isEmpty()) {
                builder.setLength(builder.length() - 2);
            }
        }

        builder.append("]}");
        return builder.toString();
    }
}
