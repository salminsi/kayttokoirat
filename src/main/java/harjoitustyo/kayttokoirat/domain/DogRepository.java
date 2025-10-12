package harjoitustyo.kayttokoirat.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository <Dog, Long> {

}
