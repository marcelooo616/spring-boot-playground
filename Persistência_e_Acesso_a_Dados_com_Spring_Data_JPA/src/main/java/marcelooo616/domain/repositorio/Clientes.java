package marcelooo616.domain.repositorio;
import marcelooo616.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    //@Query(value = " select c from Cliente c where c.nome like :nome ")
    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontraProNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);
}
