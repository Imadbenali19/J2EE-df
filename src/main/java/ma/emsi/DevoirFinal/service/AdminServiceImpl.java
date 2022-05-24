package ma.emsi.DevoirFinal.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.DevoirFinal.model.Admin;
import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.model.Role;
import ma.emsi.DevoirFinal.model.Ticket;
import ma.emsi.DevoirFinal.repository.AdminRepository;
import ma.emsi.DevoirFinal.repository.DeveloppeurRepository;
import ma.emsi.DevoirFinal.repository.RoleRepository;
import ma.emsi.DevoirFinal.repository.TicketRepository;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	DeveloppeurRepository devRepo;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<Ticket> getNonAssignedTickets() {
		return ticketRepo.getUnassinged();
	}

	@Override
	public void assignTicket(int idTicket, int idDev,int idAdmin) {
		Developpeur d = devRepo.getById(idDev);
		Admin a = adminRepo.getById(idAdmin);
		ticketRepo.assignDev(idTicket,d,a);
		
	}

	@Override
	public void addAdmin(Admin admin) {
		Role r = roleRepository.findByNom("ADMIN");
		if (r == null)
			r = new Role("ADMIN");
			roleRepository.save(r);
		admin.setRoles(Arrays.asList(r));
		
		
		admin.setRoles(Arrays.asList(r));
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		
		adminRepo.save(admin);
		
	}

	@Override
	public List<Admin> getAdmins() {
		// TODO Auto-generated method stub
		return adminRepo.findAll();
	}

	@Override
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		return ticketRepo.findAll();
	}

	@Override
	public Admin findById(int idAdmin) {
		// TODO Auto-generated method stub
		return adminRepo.getById(idAdmin);
	}
	

	

}
