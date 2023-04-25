package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Alergenos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import isst.grupo12.api.repository.AlergenosRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@AllArgsConstructor
public class AlergenosController {
    @Autowired
    private final AlergenosRepository alergenosRepository;

    @GetMapping
    public ResponseEntity<Alergenos> getAlergenos(Authentication authentication) {
        return alergenosRepository.findOneByUsuario(authentication.getName().map(usuario -> {return usuario.getId();})).map(alergenos -> {
            return ResponseEntity.ok().body(alergenos);
        }).orElse(new ResponseEntity<Alergenos>(HttpStatus.NOT_FOUND));
    }

}
