package com.example.castells_diada.services;

import com.example.castells_diada.DTOs.DiadaDTO;
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
    //UPDATE WITH PATCH
    public Diada updateDiadaCapId(Long id, DiadaDTO diadaDTO){
        Optional<Diada> existingDiada = diadaRepository.findById(id);
        if (existingDiada.isPresent()){
            Diada savedDiada = existingDiada.get();
            savedDiada.setCapId(diadaDTO.getCapId());
            return diadaRepository.save(savedDiada);
        }else{
            throw new DiadaNotFoundException("Diada con id: "+id+", no encontrada.");
        }

    }
    //UPDATE WITH PUT
    public Diada updateDiadaAllInfo (Long id, Diada diada){
        Diada existingDiada = diadaRepository.findById(id).orElseThrow(()->new DiadaNotFoundException("Diada con id: "+id+", no encontrada."));
        existingDiada.setName(diada.getName());
        existingDiada.setDiadaDate(diada.getDiadaDate());
        existingDiada.setCapId(diada.getCapId());
        return diadaRepository.save(existingDiada);
    }
    //DELETE
    public Diada deleteDiada(Long id){
        Diada existingDiada = diadaRepository.findById(id).orElseThrow(()->new DiadaNotFoundException("Diada con id: "+id+", no encontrada."));
        diadaRepository.delete(existingDiada);
        return existingDiada;
    }
}
