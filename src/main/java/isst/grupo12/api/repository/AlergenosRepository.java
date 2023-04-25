package isst.grupo12.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Alergenos;

@Repository
public interface AlergenosRepository extends JpaRepository<Alergenos, Integer>{

    Object findOneByUsuario(Integer id);
    
}
