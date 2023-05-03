package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import isst.grupo12.api.model.Consumo;
import isst.grupo12.api.model.Usuario;
import isst.grupo12.api.repository.ConsumoRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@AllArgsConstructor
public class ConsumoController {
    @Autowired
    private final ConsumoRepository consumoRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/consumo")
    public ResponseEntity<List<Consumo>> getConsumo(Authentication authentication) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Consumo>>(HttpStatus.NOT_FOUND);
        }
        List<Consumo> consumos = consumoRepository.findByusuario_id(usuario.getId());
        return ResponseEntity.ok().body(consumos);
    }

    @PostMapping("/consumo")
    public ResponseEntity<List<Consumo>> postConsumo(Authentication authentication, @RequestBody List<Consumo> consumo_req) {
        Usuario usuario = (Usuario)usuarioRepository.findOneByEmail(authentication.getName()).orElse(null);
        if(usuario == null){
            return new ResponseEntity<List<Consumo>>(HttpStatus.NOT_FOUND);
        }
        List<Consumo> old_consumos = consumoRepository.findByusuario_id(usuario.getId());
        for(Consumo consumo : old_consumos){
            consumoRepository.delete(consumo);
        }
        List<Consumo> new_consumos = consumoRepository.findByusuario_id(usuario.getId());
        for(Consumo consumo : consumo_req){
            consumo.setUsuario(usuario);
            new_consumos.add(consumo);
            consumoRepository.save(consumo);
        }
        return ResponseEntity.ok().body(new_consumos);
    };
    
}
