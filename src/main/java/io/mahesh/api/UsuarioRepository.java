package io.mahesh.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByEmailAndPassword(String email,String password);
}
