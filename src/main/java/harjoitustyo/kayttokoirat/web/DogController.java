package harjoitustyo.kayttokoirat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitustyo.kayttokoirat.domain.Breed;
import harjoitustyo.kayttokoirat.domain.BreedRepository;
import harjoitustyo.kayttokoirat.domain.Dog;
import harjoitustyo.kayttokoirat.domain.DogRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller

public class DogController {

    //@Autowired
    private DogRepository dogRepository;
    //@Autowired
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

    //siirtyy lomakkeelle add
    @GetMapping("/adddog")
    public String addDogForm(Model model) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("breed", new Breed());
        model.addAttribute("breeds", breedRepository.findAll());
        return "adddog";
    }

    
    //tallentaa rodun ja siirtyy lomakkeelle add
    @PostMapping("/addbreed")
    public String addBreed(@Valid @ModelAttribute("breed") Breed newBreed, BindingResult bindingResult, Model model) {
        if (breedRepository.existsByBreedname(newBreed.getBreedname())) {
        bindingResult.rejectValue("breedname", "error.breedname", "Rotu on jo listalla.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("dog", new Dog());
            model.addAttribute("breeds", breedRepository.findAll());
            return "adddog";
        }
        breedRepository.save(newBreed);
        return "redirect:/adddog";
    }
    

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("breeds", breedRepository.findAll()); // palauttaa rodut select-valikkoon virheilmoituksen jälkeenkin
            model.addAttribute("dog", dog); 
            model.addAttribute("breed", new Breed());                                                       
            return "adddog";
        }
        dogRepository.save(dog);
        return "redirect:/main";
    }


    @GetMapping("/delete/{id}")
    public String deleteDog(@PathVariable("id") Long id, Model model) {
        dogRepository.deleteById(id);
        return "redirect:/main";
    }

    //poistaa rodun ja siirtyy lomakkeelle add
    
    @Transactional
    @PostMapping("/deletebreed")
    public String deleteBreed(@Valid @ModelAttribute("breed") Breed breed, BindingResult bindingResult, Model model) {
        String breedname = breed.getBreedname();
        if (dogRepository.existsByBreed_Breedname(breedname)) {
        bindingResult.rejectValue("breedname", "error.breedname", "Rotua ei voi poistaa, koska se on jo käytössä.");
        }
        if (bindingResult.hasErrors()) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("breeds", breedRepository.findAll());
        return "adddog";
    }

        breedRepository.deleteByBreedname(breedname);
        return "redirect:/adddog";
    }


    //tee tänne /edit

}
