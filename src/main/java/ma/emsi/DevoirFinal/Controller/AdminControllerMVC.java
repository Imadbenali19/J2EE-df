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


import ma.emsi.DevoirFinal.model.Admin;
import ma.emsi.DevoirFinal.repository.UserRepository;
import ma.emsi.DevoirFinal.service.AdminServiceImpl;
import ma.emsi.DevoirFinal.service.DeveloppeurServieImpl;

@Controller
@RequestMapping("/mvc/admin")
public class AdminControllerMVC {
	
	@Autowired
	AdminServiceImpl adminService;
	
	@Autowired
	DeveloppeurServieImpl devService;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping
	public ModelAndView liste() {
		ModelAndView m = new ModelAndView("admin/list");
		m.addObject("admins", adminService.getAdmins());
		return m;
	}

	@GetMapping("/add")
	public String ajout(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "admin/add";

	}

	@PostMapping("/add")
	public String addAdmin(@ModelAttribute("admin") Admin admin) {
		System.out.println("added: "+admin.getId());
		System.out.println("added: "+admin.getLname());
			adminService.addAdmin(admin);
		return "admin/list";
	}
	
	
	@GetMapping("/tickets/unassigned")
	public ModelAndView getUnAssignedTickets() {
		ModelAndView m = new ModelAndView("admin/tickets");
		m.addObject("tickets", adminService.getNonAssignedTickets());
		m.addObject("devs", devService.getDevs());
		return m;
	}
	
	
	@GetMapping("/tickets/{idTicket}/assign/{idDev}")
	public String assignTicketToDev(@PathVariable int idTicket,@PathVariable int idDev) {
		ModelAndView m = new ModelAndView("admin/tickets");
		m.addObject("tickets", adminService.getNonAssignedTickets());
		m.addObject("devs", devService.getDevs());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		adminService.assignTicket(idTicket,idDev,userRepo.findByUsername(auth.getName()).getId());
		return "tickets/unassigned";
	}

}
