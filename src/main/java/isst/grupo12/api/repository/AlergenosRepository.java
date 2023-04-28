package isst.grupo12.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Alergenos;

@Repository
public interface AlergenosRepository extends JpaRepository<Alergenos, Integer>{

    List<Alergenos> findByusuario_id(Integer id);
    
}
