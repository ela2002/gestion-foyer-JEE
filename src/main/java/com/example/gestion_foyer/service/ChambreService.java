package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Chambre;
import com.example.gestion_foyer.entity.Reservation;
import com.example.gestion_foyer.repository.ChambreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ChambreService {

    private final ChambreRepository chambreRepository;

    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    public Chambre getChambreById(Long id) {
        return chambreRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Chambre avec ID " + id + " non trouvée"));
    }

    public Chambre addChambre(Chambre chambre) {
        if (chambre.getReservations() != null) {
            chambre.getReservations().forEach(reservation -> reservation.setChambre(chambre));
        }
        return chambreRepository.save(chambre);
    }

    public Chambre updateChambre(Long id, Chambre chambreDetails) {
        Chambre existing = getChambreById(id);
        existing.setNumeroChambre(chambreDetails.getNumeroChambre());
        existing.setTypeChambre(chambreDetails.getTypeChambre());
        existing.setBloc(chambreDetails.getBloc());

        if (chambreDetails.getReservations() != null) {
            chambreDetails.getReservations().forEach(reservation -> reservation.setChambre(existing));
            existing.setReservations(chambreDetails.getReservations());
        }

        return chambreRepository.save(existing);
    }

    public void deleteChambre(Long id) {
        Chambre chambre = getChambreById(id);
        chambreRepository.delete(chambre); // suppression en cascade des réservations
    }
}
