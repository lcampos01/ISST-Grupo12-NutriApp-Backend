package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Consumo;
import isst.grupo12.api.repository.ConsumoRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@AllArgsConstructor
public class ConsumoController {
    @Autowired
    private final ConsumoRepository consumoRepository;
    
}
