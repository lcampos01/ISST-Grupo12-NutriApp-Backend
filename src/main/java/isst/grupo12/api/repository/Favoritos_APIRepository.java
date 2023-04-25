package isst.grupo12.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Favoritos_API;

@Repository
public interface Favoritos_APIRepository extends JpaRepository<Favoritos_API, Integer>{
    
}
