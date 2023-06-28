package br.com.drop.repository;

import br.com.drop.model.entities.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDateRepository extends JpaRepository<PersonalData, Integer> {

}
