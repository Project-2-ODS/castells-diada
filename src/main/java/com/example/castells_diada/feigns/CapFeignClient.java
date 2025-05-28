package com.example.castells_diada.feigns;

import com.example.castells_diada.DTOs.CapFeignDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "castells-cap")
public interface CapFeignClient {
    @GetMapping("/api/cap/{id}")
    CapFeignDTO getCapById(@PathVariable Long id);
}
