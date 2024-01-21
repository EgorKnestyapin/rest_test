package de.aittr.g_31_2_rest.services;

import de.aittr.g_31_2_rest.domain.Parrot;
import de.aittr.g_31_2_rest.repositories.ParrotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParrotService {
    private ParrotRepository parrotRepository;

    public ParrotService(ParrotRepository parrotRepository) {
        this.parrotRepository = parrotRepository;
    }
    public List<Parrot> getAll() {
        return parrotRepository.getAll();
    }

    public Parrot getById(int id) {
        return parrotRepository.getById(id);
    }

    public Parrot save(Parrot parrot) {
        return parrotRepository.save(parrot);
    }

    public void deleteById(int id) {
        parrotRepository.deleteById(id);
    }
}
