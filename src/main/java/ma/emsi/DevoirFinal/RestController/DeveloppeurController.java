package ma.emsi.DevoirFinal.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.model.Ticket;
import ma.emsi.DevoirFinal.model.User;
import ma.emsi.DevoirFinal.service.DeveloppeurServieImpl;
import ma.emsi.DevoirFinal.service.UserServiceImpl;

public class DeveloppeurController {

	@Autowired
	private DeveloppeurServieImpl devService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/add")
	public void addDev(@RequestBody Developpeur dev) {
		 devService.addDev(dev);
	}
	
	
	@GetMapping("/{idDev}/tickets")
	public List<Ticket> getTickets(@PathVariable int idDev) {
		
		return devService.getDevTickets(idDev);
	}
	
	@GetMapping("/all")
	public List<User> getDevs() {
		return userService.devs();
	}
	
	@GetMapping("/all/users")
	public List<User> getUsers() {
		return userService.users();
	}
	
	
	@PostMapping("/tickets/{idTicket}/{state}")
	public void updateTicketState(@PathVariable int idTicket,@PathVariable String state) {
		devService.updateTicketStatus(idTicket,state);
	}
}
