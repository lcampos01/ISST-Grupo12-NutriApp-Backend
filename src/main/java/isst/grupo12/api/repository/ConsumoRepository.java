package isst.grupo12.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Integer>{

    List<Consumo> findByusuario_id(Integer id);
    
}
