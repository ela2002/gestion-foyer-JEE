package com.example.gestion_foyer.service;

import com.example.gestion_foyer.entity.Bloc;
import com.example.gestion_foyer.entity.Chambre;
import com.example.gestion_foyer.repository.BlocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BlocService {

    private final BlocRepository blocRepository;

    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    public Bloc getBlocById(Long id) {
        return blocRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bloc avec ID " + id + " non trouvé"));
    }

    public Bloc addBloc(Bloc bloc) {
        if (bloc.getChambres() != null) {
            bloc.getChambres().forEach(chambre -> chambre.setBloc(bloc));
        }
        return blocRepository.save(bloc);
    }

    public Bloc updateBloc(Long id, Bloc blocDetails) {
        Bloc existingBloc = getBlocById(id);
        existingBloc.setNomBloc(blocDetails.getNomBloc());
        existingBloc.setCapaciteBloc(blocDetails.getCapaciteBloc());
        existingBloc.setFoyer(blocDetails.getFoyer());

        if (blocDetails.getChambres() != null) {
            blocDetails.getChambres().forEach(chambre -> chambre.setBloc(existingBloc));
            existingBloc.setChambres(blocDetails.getChambres());
        }

        return blocRepository.save(existingBloc);
    }

    public void deleteBloc(Long id) {
        Bloc bloc = getBlocById(id);
        blocRepository.delete(bloc); // suppression en cascade des chambres
    }
}
