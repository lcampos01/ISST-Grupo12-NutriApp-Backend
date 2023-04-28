package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Alergenos;
import isst.grupo12.api.model.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import isst.grupo12.api.repository.AlergenosRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
@AllArgsConstructor
public class AlergenosController {
    @Autowired
    private final AlergenosRepository alergenosRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/alergenos")
    public ResponseEntity<List<Alergenos>> getAlergenos(Authentication authentication) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Alergenos>>(HttpStatus.NOT_FOUND);
        }
        List<Alergenos> alergenos = alergenosRepository.findByusuario_id(usuario.getId());
        return ResponseEntity.ok().body(alergenos);
    }

    @PostMapping("/alergenos")
    public ResponseEntity<List<Alergenos>> postAlergenos(Authentication authentication, @RequestBody List<Alergenos> alergeno_req) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Alergenos>>(HttpStatus.NOT_FOUND);
        }
        List<Alergenos> old_alergenos = alergenosRepository.findByusuario_id(usuario.getId());
        for(Alergenos alergeno : old_alergenos){
            alergenosRepository.delete(alergeno);
        }
        List<Alergenos> new_alergenos = alergenosRepository.findByusuario_id(usuario.getId());
        for(Alergenos alergeno : alergeno_req){
            alergeno.setUsuario(usuario);
            new_alergenos.add(alergeno);
            alergenosRepository.save(alergeno);
        }
        return ResponseEntity.ok().body(new_alergenos);
    };

}
