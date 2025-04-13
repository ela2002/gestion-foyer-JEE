package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Bloc;
import com.example.gestion_foyer.entity.Foyer;
import com.example.gestion_foyer.repository.FoyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FoyerService {

    private final FoyerRepository foyerRepository;

    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    public Foyer getFoyerById(Long id) {
        return foyerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Foyer avec ID " + id + " non trouvé"));
    }

    public Foyer addFoyer(Foyer foyer) {
        if (foyer.getBlocs() != null) {
            foyer.getBlocs().forEach(bloc -> bloc.setFoyer(foyer));
        }
        return foyerRepository.save(foyer);
    }

    public Foyer updateFoyer(Long id, Foyer foyerDetails) {
        Foyer existing = getFoyerById(id);
        existing.setNomFoyer(foyerDetails.getNomFoyer());
        existing.setCapaciteFoyer(foyerDetails.getCapaciteFoyer());

        if (foyerDetails.getBlocs() != null) {
            foyerDetails.getBlocs().forEach(bloc -> bloc.setFoyer(existing));
            existing.setBlocs(foyerDetails.getBlocs());
        }

        return foyerRepository.save(existing);
    }

    public void deleteFoyer(Long id) {
        Foyer foyer = getFoyerById(id);
        foyerRepository.delete(foyer);
    }
}
