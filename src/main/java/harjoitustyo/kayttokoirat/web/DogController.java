package harjoitustyo.kayttokoirat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitustyo.kayttokoirat.domain.DogRepository;
import jakarta.validation.Valid;





@Controller

public class DogController {

@Autowired
private DogRepository dogRepository;


@GetMapping("/main")
public String Main(Model model) {
    model.addAttribute("dogs", dogRepository.findAll());
    return "main";
}


@GetMapping("/add")
public String addDogForm(Model model) {
    model.addAttribute("dog", new Dog());
    model.addAttribute("breeds", breedRepository.findAll());
    return "adddog";
}

@PostMapping("/save")
public String save(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
    if(bindingResult.hasErrors()) {
        System.out.println("Errors errors " + dog);
        model.addAttribute(dog, dog);
        model.addAttribute(breeds, breedRepository.findAll());
        return "adddog";  //JOS VIRHE TULEE MINNE PALATAAN?
    }
    System.out.println("Save " + dog);
    dogRepository.save(dog);
    return "redirect:/main";
}


}




/* 
<input type="text" th:field="*{dogname}"/>
     <!-- Rotu-pudotusvalikko tähän -->
     <!-- <select th:field="*{breed}" >    -->
     <!--          <option th:each="cat: ${breeds}" th:value="${breed.id}" th:text="${breed.name}"></option>   -->
     <!--   </select>  -->

    <input type="text" th:field="*{gender}"/>
    <input type="text" th:field="*{birthyear}"/>
    <input type="text" th:field="*{owner}"/>
    <input type="text" th:field="*{activities}"/>
    <input type="text" th:field="*{description}"/>

    <input type="submit" value="Tallenna"/>


    */