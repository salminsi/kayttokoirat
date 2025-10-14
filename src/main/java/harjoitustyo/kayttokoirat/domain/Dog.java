package harjoitustyo.kayttokoirat.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    // @ManyToOne
    // private breed_id; //breedid tulee tietokannasta/foreignkey

    @NotEmpty(message = "Sukupuoli ei voi olla tyhjä.")
    private String gender;

    @Min(value = 2000, message = "Syntymävuosi ei voi olla ennen vuotta 2000.")
    @Max(value = 2030, message = "Syntymävuosi ei voi olla tulevaisuudessa.")
    private int birthyear;

    @NotEmpty(message = "Omistaja ei voi olla tyhjä.")
    @Size(min = 2, max = 50, message = "Kirjaimia tulee olla 1-50.")
    private String owner;

    @Size(min = 0, max = 250, message = "Kirjaimia saa olla maksimissaan 250.")
    private String activities;

    @Size(min = 0, max = 500, message = "Kirjaimia saa olla maksimissaan 500.")
    private String description;

    // rotu voisi tulla "kategoriasta" breed/breedRepository. Miten saa uuden
    // lisättyä tai "muu"?
    // entä lajit? Olisiko myös pudotusvalikko? Samoin sukupuoli??

    // konstruktorit, getterit ja setterit alkaa

    public Dog() {
    }

    public Dog(
            @NotEmpty(message = "Nimi ei voi olla tyhjä.") @Size(min = 1, max = 50, message = "Kirjaimia tulee olla 1-50.") String dogname,
            @NotEmpty(message = "Sukupuoli ei voi olla tyhjä.") String gender,
            @Min(value = 2000, message = "Syntymävuosi ei voi olla ennen vuotta 2000.") @Max(value = 2030, message = "Syntymävuosi ei voi olla tulevaisuudessa.") int birthyear,
            @NotEmpty(message = "Omistaja ei voi olla tyhjä.") @Size(min = 2, max = 50, message = "Kirjaimia tulee olla 1-50.") String owner,
            @Size(min = 0, max = 250, message = "Kirjaimia saa olla maksimissaan 250.") String activities,
            @Size(min = 0, max = 500, message = "Kirjaimia saa olla maksimissaan 500.") String description) {
        this.dogname = dogname;
        this.gender = gender;
        this.birthyear = birthyear;
        this.owner = owner;
        this.activities = activities;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDogname() {
        return dogname;
    }

    public void setDogname(String dogname) {
        this.dogname = dogname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // lisää vielä myöhemmin toString kun on kaikki sekä puuttuvat knstruktoriin ja
    // get+set!!

}
