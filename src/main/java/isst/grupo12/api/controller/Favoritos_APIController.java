package isst.grupo12.api.controller;

import org.springframework.web.bind.annotation.RestController;
import isst.grupo12.api.model.Favoritos_API;
import isst.grupo12.api.repository.Favoritos_APIRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@AllArgsConstructor
public class Favoritos_APIController {
    @Autowired
    private final Favoritos_APIRepository favoritos_APIRepository;

}
