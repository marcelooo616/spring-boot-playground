package br.com.drop.repository;

import br.com.drop.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer > {

    @Query("SELECT c FROM Contact c WHERE c.user_id.id = :userId")
    List<Contact> findByUserId(@Param("userId") Integer userId);
}
