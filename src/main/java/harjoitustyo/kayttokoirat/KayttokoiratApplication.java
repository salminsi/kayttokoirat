package harjoitustyo.kayttokoirat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.kayttokoirat.domain.Breed;
import harjoitustyo.kayttokoirat.domain.BreedRepository;

@SpringBootApplication
public class KayttokoiratApplication {

	public static void main(String[] args) {
		SpringApplication.run(KayttokoiratApplication.class, args);
	}

	@Bean
	 public CommandLineRunner demo(BreedRepository breedRepository) {
	 return (args) -> {
	 breedRepository.save(new Breed("Saksanpaimenkoira"));
	 breedRepository.save(new Breed("Kultainennoutaja"));

	 };

	}

 

}
