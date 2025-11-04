package harjoitustyo.kayttokoirat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import harjoitustyo.kayttokoirat.domain.Breed;
import harjoitustyo.kayttokoirat.domain.BreedRepository;
import harjoitustyo.kayttokoirat.domain.Dog;
import harjoitustyo.kayttokoirat.domain.DogRepository;
import jakarta.transaction.Transactional;

@SpringBootTest // kokeile toimiiko tilalla @DataJpaTest
@Transactional  //ei pysyviä muutoksia
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // OIKEA TIETOKANTA

public class DogRepositoryJpaTest {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Test
    public void addNewDog() {
        Dog dog = new Dog("Testikoira", "Uros", 2019, "Marja Mustikka", "Toko", "TK1");
        dogRepository.save(dog);
        assertThat(dog.getId()).isNotNull();
    }

    @Test
    public void findByDognameReturnOwner() {
        Dog dog = new Dog("Testikoira", "Uros", 2019, "Marja Mustikka", "Toko", "TK1");
        dogRepository.save(dog);
        assertThat(dog.getId()).isNotNull();
        
        List<Dog> dogs = dogRepository.findByDogname("Testikoira");
        assertThat(dogs).hasSize(1);
        assertThat(dogs.get(0).getOwner()).isEqualTo("Marja Mustikka");
    }

    @Test
    public void editNewDog() {
        Dog dog = new Dog("Testikoira", "Uros", 2019, "Marja Mustikka", "Toko", "TK1");
        dogRepository.save(dog);
        assertThat(dog.getId()).isNotNull();
        
        List<Dog> dogs = dogRepository.findByDogname("Testikoira");
        assertThat(dogs).hasSize(1);
        dog.setDogname("Koekoira");
        dogRepository.save(dog);
        assertThat(dogs.get(0).getOwner()).isEqualTo("Marja Mustikka");
    }

    @Test
    public void addAndDeleteNewBreed() {
        Breed breed = new Breed();
        breed.setBreedname("Kiinannoutaja");
        breedRepository.save(breed);

        List<Breed> breeds = breedRepository.findByBreedname("Kiinannoutaja");
        assertThat(breeds).isNotEmpty();
        breedRepository.delete(breed);
        List<Breed> deleteBreed = breedRepository.findByBreedname("Kiinannoutaja");
        assertThat(deleteBreed).hasSize(0);
    }

    @Test
    public void DeleteBreed() {
        List<Breed> breeds = breedRepository.findByBreedname("Kultainennoutaja");
        assertThat(breeds).isNotEmpty();
        Breed breed = breeds.get(0);
        breedRepository.delete(breed);
        List<Breed> deleteBreed = breedRepository.findByBreedname("Kultainennoutaja");
        assertThat(deleteBreed).hasSize(0);
    }

}
