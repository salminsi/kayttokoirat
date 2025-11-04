package harjoitustyo.kayttokoirat.web;

import org.springframework.security.access.prepost.PreAuthorize;
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


    private DogRepository dogRepository;

    private BreedRepository breedRepository;

    // konstruktori injektio
    public DogController(DogRepository dogRepository, BreedRepository breedRepository) {
        this.dogRepository = dogRepository;
        this.breedRepository = breedRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("dogs", dogRepository.findAll());
        return "main";
    }

    //siirtyy lomakkeelle add
    @GetMapping("/adddog")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addDogForm(Model model) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("breed", new Breed());
        model.addAttribute("breeds", breedRepository.findAll());
        return "adddog";
    }

    
    //tallentaa rodun ja siirtyy lomakkeelle add
    @PostMapping("/addbreed")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteDog(@PathVariable("id") Long id, Model model) {
        dogRepository.deleteById(id);
        return "redirect:/main";
    }

    //poistaa rodun ja siirtyy lomakkeelle add  
    @Transactional
    @PostMapping("/deletebreed")
    @PreAuthorize("hasAuthority('ADMIN')")
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


    //siirtyy editiin
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editDogForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dog", dogRepository.findById(id));
        model.addAttribute("breeds", breedRepository.findAll());
        return "editdog";
    }

    //tallenna muokattu koira
    @PostMapping("/saveedited")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEdited(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("breeds", breedRepository.findAll()); // palauttaa rodut select-valikkoon virheilmoituksen jälkeenkin
            model.addAttribute("dog", dog); 
            model.addAttribute("breed", new Breed());                                                       
            return "editdog";
        }
        dogRepository.save(dog);
        return "redirect:/main";
    }




}
