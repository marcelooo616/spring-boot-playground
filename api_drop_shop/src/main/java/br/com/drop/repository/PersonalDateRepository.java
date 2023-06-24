package br.com.drop.repository;

import br.com.drop.model.entities.PersonalData;
import br.com.drop.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonalDateRepository extends JpaRepository<PersonalData, Integer> {

}
