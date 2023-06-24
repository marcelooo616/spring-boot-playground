package br.com.drop.repository;

import br.com.drop.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddresRepository extends JpaRepository<Address, Integer> {
}
