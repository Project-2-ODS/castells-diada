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
    public Diada updateDiadaDate(Long id, DiadaDTO diadaDTO){
        Diada existingDiada = diadaRepository.findById(id).orElseThrow(()->new DiadaNotFoundException("Diada con id: "+id+", no encontrada."));
        if (diadaDTO.getDiadaDate()!=null){
            existingDiada.setDiadaDate(diadaDTO.getDiadaDate());
        }
        return diadaRepository.save(existingDiada);
    }
    //UPDATE WITH PUT
    public Diada updateDiadaAllInfo (Long id, Diada diada){
        Diada existingDiada = diadaRepository.findById(id).orElseThrow(()->new DiadaNotFoundException("Diada con id: "+id+", no encontrada."));
        existingDiada.setName(existingDiada.getName());
        existingDiada.setDiadaDate(existingDiada.getDiadaDate());
        existingDiada.setCapId(existingDiada.getCapId());
        return diadaRepository.save(existingDiada);
    }
    //DELETE
    public Diada deleteDiada(Long id){
        Diada existingDiada = diadaRepository.findById(id).orElseThrow(()->new DiadaNotFoundException("Diada con id: "+id+", no encontrada."));
        diadaRepository.delete(existingDiada);
        return existingDiada;
    }
}
