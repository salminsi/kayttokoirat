package harjoitustyo.kayttokoirat.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity

public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nimi ei voi olla tyhjä.")
    @Size(min = 1, max = 50, message = "Kirjaimia tulee olla 1-50.")
    private String dogname;

    @ManyToOne
    // private breed_id; //breedid tulee tietokannasta/foreignkey

    @NotEmpty(message = "Sukupuoli ei voi olla tyhjä.")
    private String gender;

    @Size(min = 0, max = 4, message = "Numeroita tulee olla korkeintaan 4.")
    private int birthyear;

    @NotEmpty(message = "Omistaja ei voi olla tyhjä.")
    @Size(min = 2, max = 50, message = "Kirjaimia tulee olla 1-50.")
    private String owner;

    private String activities;

    @Size(min = 0, max = 500, message = "Kirjaimia saa olla maksimissaan 500.")
    private String description;

    // rotu voisi tulla "kategoriasta" breed/breedRepository. Miten saa uuden
    // lisättyä tai "muu"?
    // entä lajit? Olisiko myös pudotusvalikko? Samoin sukupuoli??

}
