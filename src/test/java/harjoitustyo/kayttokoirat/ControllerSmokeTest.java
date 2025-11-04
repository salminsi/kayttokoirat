package harjoitustyo.kayttokoirat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


import harjoitustyo.kayttokoirat.web.DogController;
import harjoitustyo.kayttokoirat.web.DogRestController;
import harjoitustyo.kayttokoirat.web.UserController;

@SpringBootTest

public class ControllerSmokeTest {

@Autowired
private DogController dogController;

@Autowired
private DogRestController dogRestController;

@Autowired
private UserController userController;

    @Test
    public void contexLoads() throws Exception {
      assertThat(dogController).isNotNull();
      assertThat(dogRestController).isNotNull();
      assertThat(userController).isNotNull();
    }


}
