package de.aittr.g_31_2_rest.services;

import de.aittr.g_31_2_rest.domain.Dog;
import de.aittr.g_31_2_rest.repositories.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {
    private DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getAll() {
        return dogRepository.getAll();
    }

    public Dog getById(int id) {
        return dogRepository.getById(id);
    }
}
