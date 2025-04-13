package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Etudiant;
import com.example.gestion_foyer.entity.Reservation;
import com.example.gestion_foyer.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Étudiant avec ID " + id + " non trouvé"));
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        if (etudiant.getReservations() != null) {
            for (Reservation reservation : etudiant.getReservations()) {
                reservation.setEtudiant(etudiant);
            }
        }
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = getEtudiantById(id);
        etudiant.setNomEt(etudiantDetails.getNomEt());
        etudiant.setPrenomEt(etudiantDetails.getPrenomEt());
        etudiant.setCin(etudiantDetails.getCin());
        etudiant.setEcole(etudiantDetails.getEcole());
        etudiant.setDateNaissance(etudiantDetails.getDateNaissance());

        if (etudiantDetails.getReservations() != null) {
            for (Reservation reservation : etudiantDetails.getReservations()) {
                reservation.setEtudiant(etudiant);
            }
            etudiant.setReservations(etudiantDetails.getReservations());
        }

        return etudiantRepository.save(etudiant);
    }

    public void deleteEtudiant(Long id) {
        Etudiant etudiant = getEtudiantById(id);
        etudiantRepository.delete(etudiant);
    }
}
