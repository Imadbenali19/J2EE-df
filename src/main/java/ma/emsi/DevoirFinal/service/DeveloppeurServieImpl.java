package ma.emsi.DevoirFinal.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.model.Role;
import ma.emsi.DevoirFinal.model.Ticket;
import ma.emsi.DevoirFinal.repository.DeveloppeurRepository;
import ma.emsi.DevoirFinal.repository.RoleRepository;
import ma.emsi.DevoirFinal.repository.TicketRepository;

@Service
public class DeveloppeurServieImpl implements DeveloppeurService {

	@Autowired
	DeveloppeurRepository devRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void addDev(Developpeur dev) {
		Role r = roleRepository.findByNom("DEV");
		if (r == null)
			r = new Role("DEV");
			roleRepository.save(r);
		dev.setRoles(Arrays.asList(r));
		
		
		dev.setRoles(Arrays.asList(r));
		dev.setPassword(passwordEncoder.encode(dev.getPassword()));
		devRepo.save(dev);
		
	}

	@Override
	public List<Developpeur> getDevs() {
		return devRepo.findAll();
	}

	@Override
	public List<Ticket> getDevTickets(int idDev) {
		
		return ticketRepo.findByDev(idDev);
	}

	@Override
	public void updateTicketStatus(int idTicket,String status) {
		ticketRepo.updateTicketStatus(idTicket,status);
		
	}

	@Override
	public Developpeur findById(int idDev) {
		return devRepo.getById(idDev);
	}
}
