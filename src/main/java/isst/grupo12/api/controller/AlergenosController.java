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

import java.util.ArrayList;
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
        List<String> alergenos_res = new ArrayList<String>();
        for (Alergenos alergeno : alergenos) {
            alergenos_res.add(alergeno.getAlimento_alergeno());
        }
        return ResponseEntity.ok().body(alergenos);
    }

    @PostMapping("/alergenos")
    public ResponseEntity<Alergenos> postAlergenos(Authentication authentication, @RequestBody String nombre_alergeno) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<Alergenos>(HttpStatus.NOT_FOUND);
        }
        Alergenos alergeno = new Alergenos();
        alergeno.setAlimento_alergeno(nombre_alergeno);
        alergeno.setUsuario(usuario);
        alergenosRepository.save(alergeno);
        return ResponseEntity.ok().body(alergeno);
    };

}
