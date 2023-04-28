package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Favoritos;
import isst.grupo12.api.repository.FavoritosRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import isst.grupo12.api.model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@AllArgsConstructor
public class FavoritosController {
    @Autowired
    private final FavoritosRepository favoritosRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    @GetMapping("/favoritos")
    public ResponseEntity<List<Favoritos>> getFavoritos(Authentication authentication) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Favoritos>>(HttpStatus.NOT_FOUND);
        }
        List<Favoritos> favorito = favoritosRepository.findByusuario_id(usuario.getId());
        
        return ResponseEntity.ok().body(favorito);
        // .orElse(new ResponseEntity<Alergenos>(HttpStatus.NOT_FOUND));
    }

    
    @PostMapping("/favoritos")
    public ResponseEntity<List<Favoritos>> postAlergenos(Authentication authentication, @RequestBody Favoritos favoritos_req) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Favoritos>>(HttpStatus.NOT_FOUND);
        }
        
            Favoritos favorito = new Favoritos();
            favorito.setUrl(favoritos_req.getUrl());
            favorito.setUsuario(usuario);
            favoritosRepository.save(favorito);
        
        return ResponseEntity.ok().body(favoritosRepository.findByusuario_id(usuario.getId()));
    };

    @DeleteMapping("/favoritos")
    public ResponseEntity<List<Favoritos>> deleteAlergenos(Authentication authentication, @RequestBody Favoritos favoritos_req) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Favoritos>>(HttpStatus.NOT_FOUND);
        }
        String url = favoritos_req.getUrl();
        Favoritos favorito = (Favoritos)favoritosRepository.findByUsuarioAndUrl(usuario, url).orElse(null);
        if(favorito == null){
            return new ResponseEntity<List<Favoritos>>(HttpStatus.NOT_FOUND);
        }
        favoritosRepository.delete(favorito);
        
        return ResponseEntity.ok().body(favoritosRepository.findByusuario_id(usuario.getId()));
    };
}
