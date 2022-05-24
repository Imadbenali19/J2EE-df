package ma.emsi.DevoirFinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.emsi.DevoirFinal.model.User;
import ma.emsi.DevoirFinal.repository.UserRepository;
import ma.emsi.DevoirFinal.service.UserService;

@Controller
@RequestMapping("/")
public class HomeControllerMVC {
/*
	@GetMapping
	public String home() {
		return "home";
	}
	
	@GetMapping("login")
	public String login() {
		return "connexion";
	}*/
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String index(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("user", u);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		if(u==null)
			return "connexion";
		
		return "redirect:/";	
		
			
	}
	
	@GetMapping("/error")
	public String error(Model m) {
		m.addAttribute("user", new User());
		return "error";
	}

	@GetMapping("/inscription")
	public String inscription(Model m) {
		m.addAttribute("user", new User());
		return "inscription";
	}
	
	@GetMapping("/profile")
	public String profile(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("user", u);
		return "profile";
	}
	

	@PostMapping("/inscription")
	public String insc(@ModelAttribute User user) {
		userService.save(user);
		return "login";
	}
}
