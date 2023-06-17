package marcelooo616.domain.repository;

import marcelooo616.domain.entity.Cliente;
import marcelooo616.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);


}
