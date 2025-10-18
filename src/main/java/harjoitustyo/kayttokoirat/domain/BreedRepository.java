package harjoitustyo.kayttokoirat.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    boolean existsByBreedname(String breedname);
    void deleteByBreedname(String breedname);
}
