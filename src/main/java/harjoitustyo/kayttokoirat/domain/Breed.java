package harjoitustyo.kayttokoirat.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity

public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String breedname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "breed")
    private List<Dog> dogs;

    

    
    public Breed() {
    }

    public Breed(String breedname) {
        this.breedname = breedname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedname() {
        return breedname;
    }

    public void setBreedname(String breedname) {
        this.breedname = breedname;
    }

    @Override
    public String toString() {
        return "Breed [id=" + id + ", breedname=" + breedname + "]";
    }

    

}
