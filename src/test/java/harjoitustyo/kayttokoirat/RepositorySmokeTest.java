package harjoitustyo.kayttokoirat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import javax.sql.DataSource;

import harjoitustyo.kayttokoirat.domain.AppUserRepository;
import harjoitustyo.kayttokoirat.domain.BreedRepository;
import harjoitustyo.kayttokoirat.domain.DogRepository;


@SpringBootTest
public class RepositorySmokeTest {

@Autowired
private AppUserRepository appUserRepository;

@Autowired
private BreedRepository breedRepository;

@Autowired
private DogRepository dogRepository;

@Autowired
private DataSource dataSource;

  

@Test
public void repositoriesAreLoaded() throws Exception {
    assertThat(appUserRepository).isNotNull();
    assertThat(breedRepository).isNotNull();
    assertThat(dogRepository).isNotNull();
    assertThat(dataSource.getConnection()).isNotNull(); // testaa tietokantayhteyden
  }

}

