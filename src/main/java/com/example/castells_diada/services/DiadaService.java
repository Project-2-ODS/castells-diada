package com.example.castells_diada.services;

import com.example.castells_diada.exceptions.DiadaNotFoundException;
import com.example.castells_diada.models.Diada;
import com.example.castells_diada.repositories.DiadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiadaService {
@Autowired
    DiadaRepository diadaRepository;

//CREATE
    public Diada createDiada(Diada diada){
        return diadaRepository.save(diada);
    }

    //READ
    public Diada findDiadaById(Long id){
        Optional<Diada> foundDiada = diadaRepository.findById(id);
        if (foundDiada.isPresent()){
            return foundDiada.get();
        }else{
            throw new DiadaNotFoundException("Diada no encontrada");
        }
    }
    //UPDATE
}
