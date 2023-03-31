package io.mahesh.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByEmailAndContraseña(String email,String contraseña);
}
