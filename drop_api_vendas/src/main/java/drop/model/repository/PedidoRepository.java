package drop.model.repository;

import drop.model.entities.Cliente;
import drop.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {


    @Query("select p from Pedido p where id like :id")
    List<Pedido> findByName(@Param("id") Integer id);



}
