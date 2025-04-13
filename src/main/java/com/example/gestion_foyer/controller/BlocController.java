package com.example.gestion_foyer.controller;

import com.example.gestion_foyer.entity.Bloc;
import com.example.gestion_foyer.service.BlocService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blocs")
@RequiredArgsConstructor
public class BlocController {
    private final BlocService blocService;

    @GetMapping
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/{id}")
    public Bloc getBloc(@PathVariable Long id) {
        return blocService.getBlocById(id);
    }

    @PostMapping
    public Bloc createBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/{id}")
    public Bloc updateBloc(@PathVariable Long id, @RequestBody Bloc bloc) {
        return blocService.updateBloc(id, bloc);
    }

    @DeleteMapping("/{id}")
    public void deleteBloc(@PathVariable Long id) {
        blocService.deleteBloc(id);
    }
}

