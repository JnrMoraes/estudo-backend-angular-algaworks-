package com.algamoneyapi.algaworksalgamoneyapi.repository;

import com.algamoneyapi.algaworksalgamoneyapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findyEmail(String email);



}
