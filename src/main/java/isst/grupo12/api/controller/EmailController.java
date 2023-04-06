package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import isst.grupo12.api.model.Usuario;
import isst.grupo12.api.repository.UsuarioRepository;
import isst.grupo12.api.service.SendEmail;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@RestController
@AllArgsConstructor
public class EmailController {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    
    @PutMapping("/send-email")
    public ResponseEntity<SendEmail> updateUser(Authentication authentication) {
        return usuarioRepository.findOneByEmail(authentication.getName()).map(usuario -> {
            SendEmail sendEmail = new SendEmail();
            sendEmail.sendEmail(usuario.getEmail(), "Prueba", "Hola");
            return ResponseEntity.ok().body(sendEmail);
        }).orElse(new ResponseEntity<SendEmail>(HttpStatus.NOT_FOUND));
    }
}