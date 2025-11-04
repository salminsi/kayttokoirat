package harjoitustyo.kayttokoirat.domain;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository <Dog, Long> {
    boolean existsByBreed_Breedname(String breedname);
    List<Dog> findByDogname(String dogname);
}
