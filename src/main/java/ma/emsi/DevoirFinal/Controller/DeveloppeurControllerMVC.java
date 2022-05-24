package ma.emsi.DevoirFinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.repository.UserRepository;
import ma.emsi.DevoirFinal.service.ClientServiceImpl;
import ma.emsi.DevoirFinal.service.DeveloppeurServieImpl;

@Controller
@RequestMapping("/mvc/dev")
public class DeveloppeurControllerMVC {

	@Autowired
	DeveloppeurServieImpl devService;
	
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping("/add")
	public String add(Model model) {
		Developpeur dev = new Developpeur();
		model.addAttribute("dev", dev);
		return "dev/add";

	}
	
	@GetMapping("/getTickets")
	public ModelAndView tickets(Model model) {
		ModelAndView m = new ModelAndView("dev/tickets");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int id = userRepo.findByUsername(auth.getName()).getId();
		
		m.addObject("tickets", devService.getDevTickets(id));
		return m;

	}
	
	
	@GetMapping("/updateTicket/{idTicket}/{state}")
	public ModelAndView updateTicket(Model model,@PathVariable int idTicket,@PathVariable String state) {
		ModelAndView m = new ModelAndView("dev/tickets");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int id = userRepo.findByUsername(auth.getName()).getId();
		devService.updateTicketStatus(idTicket,state);
		m.addObject("tickets", devService.getDevTickets(id));
		return m;

	}

	@PostMapping("/add")
	public String add(@ModelAttribute("dev") Developpeur dev) {

		
		devService.addDev(dev);
		return "login";
	}
}
