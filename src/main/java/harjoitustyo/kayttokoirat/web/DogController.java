package harjoitustyo.kayttokoirat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitustyo.kayttokoirat.domain.BreedRepository;
import harjoitustyo.kayttokoirat.domain.Dog;
import harjoitustyo.kayttokoirat.domain.DogRepository;
import jakarta.validation.Valid;

@Controller

public class DogController {

    // @Autowired
    private DogRepository dogRepository;
    // @Autowired
    private BreedRepository breedRepository;

    // konstruktori injektio
    public DogController(DogRepository dogRepository, BreedRepository breedRepository) {
        this.dogRepository = dogRepository;
        this.breedRepository = breedRepository;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("dogs", dogRepository.findAll());
        return "main";
    }

    @GetMapping("/adddog")
    public String addDogForm(Model model) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("breeds", breedRepository.findAll());
        return "adddog";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("breeds", breedRepository.findAll()); // palauttaa rodut select-valikkoon
                                                                     // virheilmoituksen jälkeenkin
            return "adddog"; // JOS VIRHE TULEE MINNE PALATAAN?
        }
        dogRepository.save(dog);
        return "redirect:/main";
    }

}
