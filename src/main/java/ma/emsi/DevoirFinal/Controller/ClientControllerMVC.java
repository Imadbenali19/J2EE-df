package ma.emsi.DevoirFinal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Ticket;
import ma.emsi.DevoirFinal.repository.UserRepository;
import ma.emsi.DevoirFinal.service.ClientServiceImpl;

@Controller
@RequestMapping("/mvc/client")
public class ClientControllerMVC {
	
	@Autowired
	ClientServiceImpl clientService;
	
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping("/add")
	public String add(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "client/add";

	}

	@PostMapping("/add")
	public String add(@ModelAttribute("client") Client client) {

		clientService.addClient(client);
		return "client/list";
	}
	
	@GetMapping("/getTickets")
	public ModelAndView liste() {
		ModelAndView m = new ModelAndView("client/list");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int id = userRepo.findByUsername(auth.getName()).getId();
		
		m.addObject("tickets", clientService.getClientTickets(id));
		return m;
	}
	
	
	@GetMapping("/addTicket")
	public String addTicket(Model model) {
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "client/addTicket";

	}

	@PostMapping("/addTicket")
	public String addTicket(@ModelAttribute("ticket") Ticket t) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		t.setClient(userRepo.findByUsername(auth.getName()));
		t.setState("PENDING");
		clientService.createTicket(t);
		return "redirect:getTickets";
	}

}
