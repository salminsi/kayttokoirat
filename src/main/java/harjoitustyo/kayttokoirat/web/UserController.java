package harjoitustyo.kayttokoirat.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import harjoitustyo.kayttokoirat.domain.AppUser;
import harjoitustyo.kayttokoirat.domain.AppUserRepository;
import harjoitustyo.kayttokoirat.domain.SignupForm;
import jakarta.validation.Valid;

//käsittelee signup-rekisteröinnin pyyntöjä

@Controller
public class UserController {

    private AppUserRepository appUserReporitory; 
	
	public UserController(AppUserRepository appUserReporitory) {
		this.appUserReporitory = appUserReporitory; 
	}

    @GetMapping("/signup")
    public String addUser(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @PostMapping("/saveuser")
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) {
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	AppUser newUser = new AppUser();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (appUserReporitory.findByUsername(signupForm.getUsername()) == null) {
		    		appUserReporitory.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Käyttäjänimi on jo olemassa.");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Salasanat eivät täsmää.");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    



}
