package drop.model.repository;

import drop.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("select p from Produto p where categoria like %:categoria%")
    List<Produto> findByCategory(@Param("categoria") String categoria);
}
