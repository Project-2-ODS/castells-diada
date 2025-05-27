package com.example.castells_diada.controllers;


import com.example.castells_diada.models.Diada;
import com.example.castells_diada.services.DiadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diada")
public class DiadaController {
    @Autowired
    DiadaService diadaService;

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diada createDiada(@RequestBody @Valid Diada diada){
        return diadaService.createDiada(diada);
    }
}
