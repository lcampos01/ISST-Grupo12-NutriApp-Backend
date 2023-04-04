package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import isst.grupo12.api.repository.ContactoRepository;
import lombok.AllArgsConstructor;
import isst.grupo12.api.model.Contacto;

@RestController
@RequestMapping("contactos")
@AllArgsConstructor
public class ContactoController {
    
    private final ContactoRepository contactoRepository;
    
    @GetMapping
    public List<Contacto> listContacto() {
        return contactoRepository.findAll();
    }
}
