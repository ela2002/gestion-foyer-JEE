package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Universite;
import com.example.gestion_foyer.repository.UniversiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversiteService {

    private final UniversiteRepository universiteRepository;

    public Universite createUniversite(Universite universite) {
        if (universite.getFoyer() != null) {
            universite.getFoyer().setUniversite(universite); // maintenir cohérence bidirectionnelle
        }
        return universiteRepository.save(universite);
    }

    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    public Optional<Universite> getUniversiteById(Long id) {
        return universiteRepository.findById(id);
    }

    public Universite updateUniversite(Long id, Universite updatedUniversite) {
        return universiteRepository.findById(id).map(existing -> {
            existing.setNomUniversite(updatedUniversite.getNomUniversite());
            existing.setAdresse(updatedUniversite.getAdresse());

            if (updatedUniversite.getFoyer() != null) {
                updatedUniversite.getFoyer().setUniversite(existing);
                existing.setFoyer(updatedUniversite.getFoyer());
            }

            return universiteRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Université non trouvée"));
    }

    public void deleteUniversite(Long id) {
        Universite universite = universiteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Université non trouvée"));
        universiteRepository.delete(universite);
    }
}
