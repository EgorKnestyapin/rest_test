package de.aittr.g_31_2_rest.controllers;

import de.aittr.g_31_2_rest.domain.Dog;
import de.aittr.g_31_2_rest.services.DogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {
    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public List<Dog> getAllDogs() {
       return dogService.getAll();
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable int id) {
        return dogService.getById(id);
    }

    @GetMapping("/get")
    public Dog getDogById2(@RequestParam int id) {
        return  dogService.getById(id);
    }
}
