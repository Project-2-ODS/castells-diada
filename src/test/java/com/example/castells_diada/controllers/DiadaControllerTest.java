package com.example.castells_diada.controllers;

import com.example.castells_diada.models.Diada;
import com.example.castells_diada.repositories.DiadaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalDate;



@SpringBootTest
public class DiadaControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    DiadaRepository diadaRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private Diada diada;

    @BeforeEach
    public void setUp(){
        diada = new Diada();
        diada.setName("Diada del Testing");
        diada.setDiadaDate(LocalDate.of(2026, 7, 11));
        diada.setCapId(1L);
        diadaRepository.save(diada);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown(){
        Diada diada = diadaRepository.findByName("Diada del Testing");
        if (diada!=null){
            diadaRepository.delete(diada);
        }
    }

    @Test
    public void createDiada() throws Exception {
        String diadaJSON = objectMapper.writeValueAsString(diada);

        MvcResult result = mockMvc.perform(post("/api/diada")
                .contentType(MediaType.APPLICATION_JSON)
                .content(diadaJSON)
        ).andExpect(status().isCreated()).andReturn();
        String stringResponse = result.getResponse().getContentAsString();
        assertTrue(stringResponse.contains("Diada del Testing"));
    }

    @Test
    public void updateDiada() throws Exception{
        String diadaJSON = objectMapper.writeValueAsString(diada);

        MvcResult result = mockMvc.perform(put("/api/diada/" +diada.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(diadaJSON)
        ).andExpect(status().isOk()).andReturn();
        String stringResponse = result.getResponse().getContentAsString();
        assertTrue(stringResponse.contains("Diada del Testing"));
    }
    @Test
    public void deleteDiada() throws Exception {
        String diadaJSON = objectMapper.writeValueAsString(diada);

        MvcResult result = mockMvc.perform(delete("/api/diada/" +diada.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(diadaJSON)
        ).andExpect(status().isNoContent()).andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Diada del Testing"));
    }
}
