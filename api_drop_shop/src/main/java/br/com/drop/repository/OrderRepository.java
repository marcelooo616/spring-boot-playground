package br.com.drop.repository;

import br.com.drop.model.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(" select o from Order o left join fetch o.itemsList where o.id = :id ")
    Optional<Order> findByIdFetchItens(@Param("id") Integer id);
}
