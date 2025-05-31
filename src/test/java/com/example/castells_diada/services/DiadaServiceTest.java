package com.example.castells_diada.services;

import com.example.castells_diada.DTOs.DiadaDTO;
import com.example.castells_diada.models.Diada;
import com.example.castells_diada.repositories.DiadaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DiadaServiceTest {
    @Autowired
    DiadaService diadaService;
    @Autowired
    DiadaRepository diadaRepository;
    private Diada diada;

    @BeforeEach
    public void setUp(){
        diada = new Diada();
        diada.setName("Diada del Testing");
        diada.setDiadaDate(LocalDate.of(2026,7,23));
        diada.setCapId(1L);
        System.out.println("DIADA DEL TEST: " + diada);
        diadaService.createDiada(diada);

    }
    @AfterEach
    public void tearDown(){
        diadaRepository.delete(diada);
    }

    @Test
    @DisplayName("Crear Diada")
    public void createDiada(){
        assertNotNull(diada.getId(), "La Diada deberia ser null por que no pasa por BBDD");
        assertEquals("Diada del Testing", diada.getName(), "Nombre correcto");
        assertEquals(LocalDate.of(2026,7,23), diada.getDiadaDate(), "Dia de la Diada correcta");
        assertEquals(1L, diada.getCapId(), "Cap de la Diada correcto");
    }

    @Test
    @DisplayName("Buscar Diada por ID")
    public void findDiadaById(){
        Diada foundDiada = diadaService.findDiadaById(diada.getId());
        assertNotNull(foundDiada);
        assertEquals(diada.getDiadaDate(), foundDiada.getDiadaDate());
        assertEquals(diada.getName(), foundDiada.getName());
        assertEquals(diada.getCapId(), foundDiada.getCapId());
        System.out.println("DIADA A COMPARAR: " +diada);
    }

    @Test
    @DisplayName("Actualizar Cap de la Diada")
    public void updateCadDiada(){
        DiadaDTO updateDiada = new DiadaDTO();
        updateDiada.setCapId(2L);
        Diada updatedDiada = diadaService.updateDiadaCapId(diada.getId(), updateDiada);
        assertNotNull(updatedDiada);
        assertEquals(2L, updatedDiada.getCapId());
        System.out.println("DIADA A COMPARAR: " +updatedDiada);
    }

    @Test
    @DisplayName("Eliminar Diada")
    public void deleteDiada(){
        diadaService.deleteDiada(diada.getId());
        assertFalse(diadaRepository.existsById(diada.getId()), "Deberia estar eliminado de la BBDD");
        System.out.println("DIADA A COMPARAR: " +diada);
    }
}
