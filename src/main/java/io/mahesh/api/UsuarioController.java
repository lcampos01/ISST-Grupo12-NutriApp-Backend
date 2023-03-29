package io.mahesh.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public Usuario Register(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @PostMapping("/login")
    public Usuario Login(@RequestBody Usuario usuario) {
        Usuario Usuario_antiguo = usuarioRepository.findByEmailAndPassword(usuario.Email, usuario.Contraseña);
        return Usuario_antiguo;
    }
}
