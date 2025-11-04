package harjoitustyo.kayttokoirat.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 50, message = "Kirjaimia tulee olla 1-50.")
    private String dogname;

    @ManyToOne
    @JoinColumn(name="breedid")
    private Breed breed; //breed_id tulee tietokannasta/foreignkey

    private String gender;

    @Min(value = 2000, message = "Syntymävuosi ei voi olla ennen vuotta 2000.")
    @Max(value = 2030, message = "Syntymävuosi ei voi olla tulevaisuudessa.")
    private int birthyear;

    @Column(name = "dogowner")
    @Size(min = 2, max = 50, message = "Kirjaimia tulee olla 2-50.")
    private String owner;

    @Size(min = 1, max = 300, message = "Lajit, joissa ansioitunut/harrastaa.")
    private String activities;

    @Size(min = 1, max = 500, message = "Lyhyt kuvailu koiran ominaisuuksista (max. 500 kirjainta).")
    private String description;


    public Dog() {
    }

    public Dog(String dogname, String gender, int birthyear, String owner, String activities, String description) {
        this.dogname = dogname;
        this.gender = gender;
        this.birthyear = birthyear;
        this.owner = owner;
        this.activities = activities;
        this.description = description;
    }

    public Dog(String dogname, Breed breed, String gender, int birthyear, String owner, String activities, String description) {
        this.dogname = dogname;
        this.breed = breed;
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

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog [id=" + id + ", dogname=" + dogname + ", breed=" + breed + ", gender=" + gender + ", birthyear="
                + birthyear + ", owner=" + owner + ", activities=" + activities + ", description=" + description + "]";
    }

    

}
