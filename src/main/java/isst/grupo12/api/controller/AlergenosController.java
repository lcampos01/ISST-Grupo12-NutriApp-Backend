package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Alergenos;
import isst.grupo12.api.repository.AlergenosRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@AllArgsConstructor
public class AlergenosController {
    @Autowired
    private final AlergenosRepository alergenosRepository;

}
