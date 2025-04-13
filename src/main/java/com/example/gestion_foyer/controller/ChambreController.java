package com.example.gestion_foyer.controller;

import com.example.gestion_foyer.entity.Chambre;
import com.example.gestion_foyer.service.ChambreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chambres")
@RequiredArgsConstructor
public class ChambreController {
    private final ChambreService chambreService;

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/{id}")
    public Chambre getChambre(@PathVariable Long id) {
        return chambreService.getChambreById(id);
    }

    @PostMapping
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/{id}")
    public Chambre updateChambre(@PathVariable Long id, @RequestBody Chambre chambre) {
        return chambreService.updateChambre(id, chambre);
    }

    @DeleteMapping("/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }
}
