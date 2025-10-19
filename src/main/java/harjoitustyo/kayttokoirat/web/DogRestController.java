package harjoitustyo.kayttokoirat.web;

import org.springframework.web.bind.annotation.RestController;

import harjoitustyo.kayttokoirat.domain.Breed;
import harjoitustyo.kayttokoirat.domain.BreedRepository;
import harjoitustyo.kayttokoirat.domain.Dog;
import harjoitustyo.kayttokoirat.domain.DogRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

public class DogRestController {

    private final DogRepository dogRepository;
    private final BreedRepository breedRepository;

    public DogRestController(DogRepository dogRepository, BreedRepository breedRepository) {
        this.dogRepository = dogRepository;
        this.breedRepository = breedRepository;
    }

    // listaa koirat
    @GetMapping("/dogs")
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    // listaa rodut
    @GetMapping("/breeds")
    public List<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }

    // näytä yksi koira
    @GetMapping("/dogs/{id}")
    public Optional<Dog> getDogById(@PathVariable Long id) {
        return dogRepository.findById(id);
    }

    // lisää koira
    @PostMapping("/dogs")
    public Dog saveDog(@RequestBody Dog dog) {
        return dogRepository.save(dog);
    }

    // lisää rotu
    @PostMapping("/breeds")
    public Breed saveBreed(@RequestBody Breed breed) {
        return breedRepository.save(breed);
    }

    // muokkaa koiraa
    @PutMapping("/dogs/{id}")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog updateDog) {
        updateDog.setId(id);
        return dogRepository.save(updateDog);
    }

    // poista koira
    @DeleteMapping("/dogs/{id}")
    public List<Dog> deleteDog(@PathVariable Long id) {
        dogRepository.deleteById(id);
        return dogRepository.findAll();
    }



}