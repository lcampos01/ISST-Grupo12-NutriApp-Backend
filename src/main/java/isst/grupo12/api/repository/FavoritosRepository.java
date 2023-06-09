package isst.grupo12.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isst.grupo12.api.model.Favoritos;

@Repository
public interface FavoritosRepository extends JpaRepository<Favoritos, Integer>{
    
    List<Favoritos> findByusuario_id(Integer id);

    Optional<Favoritos> findByUsuario_idAndBarcode(Integer usuario_id, String barcode);
}
