package isst.grupo12.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Contacto;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer>{
    
}
