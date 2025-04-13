package com.example.gestion_foyer.controller;

import com.example.gestion_foyer.entity.Universite;
import com.example.gestion_foyer.service.UniversiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/universites")
@RequiredArgsConstructor
public class UniversiteController {
    private final UniversiteService universiteService;


    @PostMapping
    public Universite createUniversite(@RequestBody Universite universite) {
        return universiteService.createUniversite(universite);
    }

    @GetMapping
    public List<Universite> getAllUniversites() {
        return universiteService.getAllUniversites();
    }

    @GetMapping("/{id}")
    public Universite getUniversiteById(@PathVariable Long id) {
        return universiteService.getUniversiteById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Universite updateUniversite(@PathVariable Long id, @RequestBody Universite universite) {
        return universiteService.updateUniversite(id, universite);
    }

    @DeleteMapping("/{id}")
    public void deleteUniversite(@PathVariable Long id) {
        universiteService.deleteUniversite(id);
    }
}

