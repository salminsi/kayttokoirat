package harjoitustyo.kayttokoirat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import harjoitustyo.kayttokoirat.domain.AppUser;
import harjoitustyo.kayttokoirat.domain.AppUserRepository;
import harjoitustyo.kayttokoirat.domain.Breed;
import harjoitustyo.kayttokoirat.domain.BreedRepository;

@SpringBootApplication
public class KayttokoiratApplication {

	public static void main(String[] args) {
		SpringApplication.run(KayttokoiratApplication.class, args);
	}

// palautettu bean kun on deployment
	@Bean
	public CommandLineRunner demo(BreedRepository breedRepository, AppUserRepository appUserRepository) {
		return (args) -> {
			breedRepository.save(new Breed("Bordercollie"));
			breedRepository.save(new Breed("Kultainennoutaja"));
			breedRepository.save(new Breed("Isovillakoira"));
			breedRepository.save(new Breed("Saksanpaimenkoira"));

			// luo käyttäjät admin/admin ja user/user
			AppUser user1 = new AppUser("user", "$2a$10$cKAXCXi3GdELDkJXkVbyZejKsnVP54lto0.MLzbvHYjGIoErpkGWO", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$R.ntvNy3FSQEgXLsNJh5xumIPlabSrtdBRMIzZAv8A0K.P.966VWW",
					"ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);

		};

	}

}
