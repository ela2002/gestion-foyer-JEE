package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Reservation;
import com.example.gestion_foyer.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Réservation avec ID " + id + " non trouvée"));
    }

    public Reservation addReservation(Reservation reservation) {
        if (reservation.getEtudiant() != null) {
            reservation.getEtudiant().getReservations().add(reservation);
        }
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation details) {
        Reservation r = getReservationById(id);
        r.setAnneeUniversitaire(details.getAnneeUniversitaire());
        r.setEstValide(details.isEstValide());

        if (details.getEtudiant() != null) {
            details.getEtudiant().getReservations().add(r);
            r.setEtudiant(details.getEtudiant());
        }

        r.setChambre(details.getChambre());

        return reservationRepository.save(r);
    }

    public void deleteReservation(Long id) {
        Reservation r = getReservationById(id);
        reservationRepository.delete(r);
    }
}
