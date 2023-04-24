package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Alimentos;
import isst.grupo12.api.repository.AlimentosRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@AllArgsConstructor
public class AlimentosController {
    @Autowired
    private final AlimentosRepository alimentosRepository;
}
