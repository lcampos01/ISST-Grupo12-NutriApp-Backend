package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.RestController;

import isst.grupo12.api.model.Alergenos;
import isst.grupo12.api.model.Usuario;
import isst.grupo12.api.model.UsuarioRegistro;
import isst.grupo12.api.repository.AlergenosRepository;
import isst.grupo12.api.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    private final AlergenosRepository alergenosRepository;
    
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<Usuario> registration(@RequestBody UsuarioRegistro usuario) {
        if(!usuarioRepository.findOneByEmail(usuario.getEmail()).isEmpty()) {
            return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        nuevoUsuario.setAltura(usuario.getAltura());
        nuevoUsuario.setPeso(usuario.getPeso());
        nuevoUsuario.setSexo(usuario.getSexo());
        nuevoUsuario.setFecha_nacimiento(usuario.getFecha_nacimiento());
        nuevoUsuario.setActividad_diaria(usuario.getActividad_diaria());
        nuevoUsuario.setIsAdmin(0);
        nuevoUsuario.setObjetivo(usuario.getObjetivo());
        nuevoUsuario.setNum_objetivo(usuario.getNum_objetivo());
        usuarioRepository.save(nuevoUsuario);

        Usuario usuarioR = (Usuario)usuarioRepository.findOneByEmail(usuario.getEmail()).orElse(null);
        if(usuarioR == null){
            return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
        }
        List<Alergenos> alergeno_req = usuario.getAlergenos();
        for(Alergenos alergeno : alergeno_req){
            alergeno.setUsuario(usuarioR);
            alergenosRepository.save(alergeno);
        }
        return ResponseEntity.ok().body(nuevoUsuario);
    }

    @GetMapping("/currentuser")
    public ResponseEntity<Usuario> currentUserName(Authentication authentication) {
        return usuarioRepository.findOneByEmail(authentication.getName()).map(usuario -> {
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/modify-user")
    public ResponseEntity<Usuario> updateUser(@RequestBody Usuario Usuario, Authentication authentication) {
        return usuarioRepository.findOneByEmail(authentication.getName()).map(usuario -> {
            usuario.setNombre(Usuario.getNombre());
            usuario.setAltura(Usuario.getAltura());
            usuario.setPeso(Usuario.getPeso());
            usuario.setSexo(Usuario.getSexo());
            usuario.setFecha_nacimiento(Usuario.getFecha_nacimiento());
            usuario.setActividad_diaria(Usuario.getActividad_diaria());
            usuario.setIsAdmin(Usuario.getIsAdmin());
            usuario.setImagenPerfil(Usuario.getImagenPerfil());
            usuario.setObjetivo(Usuario.getObjetivo());
            usuario.setNum_objetivo(Usuario.getNum_objetivo());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(usuario);
        }).orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }
}
