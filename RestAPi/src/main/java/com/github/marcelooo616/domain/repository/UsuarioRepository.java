package com.github.marcelooo616.domain.repository;


import com.github.marcelooo616.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByLogin(String login);
}
