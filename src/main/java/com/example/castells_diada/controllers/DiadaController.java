package com.example.castells_diada.controllers;


import com.example.castells_diada.DTOs.CapFeignDTO;
import com.example.castells_diada.DTOs.DiadaDTO;
import com.example.castells_diada.exceptions.DiadaNotFoundException;
import com.example.castells_diada.feigns.CapFeignClient;
import com.example.castells_diada.models.Diada;
import com.example.castells_diada.services.DiadaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/diada")
public class DiadaController {
    @Autowired
    DiadaService diadaService;
    @Autowired
    CapFeignClient capFeignClient;

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Diada createDiada(@RequestBody @Valid Diada diada){
        return diadaService.createDiada(diada);
    }
    //READ
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiadaById(@PathVariable Long id){
        try{
            Diada foundDiada = diadaService.findDiadaById(id);
            Map<String,Object> response = new HashMap<>();
            CapFeignDTO foundCap = capFeignClient.getCapById(foundDiada.getCapId());//TODO: darlo vuelta para que el postman salga DIADA y despues CAP
            response.put("Diada", foundDiada);
            response.put("Cap", foundCap);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DiadaNotFoundException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    //UPDATE ONLY DIADA DATE
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDiadaCapId(@PathVariable Long id,@RequestBody DiadaDTO diadaDTO){
        try {
            Diada existingDiada = diadaService.updateDiadaCapId(id, diadaDTO);
            return ResponseEntity.ok(existingDiada);
        }catch (DiadaNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }
    //UPDATE ALL INFO
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiadaAllInfo(@PathVariable Long id, @RequestBody Diada diada){
        try {
            Diada existingDiada = diadaService.updateDiadaAllInfo(id, diada);
            return ResponseEntity.ok(existingDiada);
        }catch (DiadaNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }
    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiada(@PathVariable Long id){
        try{
            Diada existingDiada = diadaService.deleteDiada(id);
            return ResponseEntity.noContent().build();
        }catch (DiadaNotFoundException exception){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }

}
