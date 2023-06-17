package drop.model.repository;




import drop.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where nome like :nome")
    List<Cliente> findByName(@Param("nome") String nome);

    @Query("select c from Cliente c where id like :id")
    List<Cliente> findAllById(@Param("id") Integer id);

    @Query("select c from Cliente c where endereco like %:endereco%")
    List<Cliente> findByEndereco(@Param("endereco") String endereco);
}
