package com.example.gestion_foyer.controller;

import com.example.gestion_foyer.entity.Foyer;
import com.example.gestion_foyer.service.FoyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foyers")
@RequiredArgsConstructor
public class FoyerController {
    private final FoyerService foyerService;

    @GetMapping
    public List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @GetMapping("/{id}")
    public Foyer getFoyer(@PathVariable Long id) {
        return foyerService.getFoyerById(id);
    }

    @PostMapping
    public Foyer createFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @PutMapping("/{id}")
    public Foyer updateFoyer(@PathVariable Long id, @RequestBody Foyer foyer) {
        return foyerService.updateFoyer(id, foyer);
    }

    @DeleteMapping("/{id}")
    public void deleteFoyer(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }

    }

