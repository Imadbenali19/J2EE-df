package ma.emsi.DevoirFinal.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Role;
import ma.emsi.DevoirFinal.model.Ticket;
import ma.emsi.DevoirFinal.repository.ClientRepository;
import ma.emsi.DevoirFinal.repository.RoleRepository;
import ma.emsi.DevoirFinal.repository.TicketRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepo;
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<Ticket> getClientTickets(int clientId) {
		Client client = clientRepo.getById(clientId);
		
		return client.getTickets();
	}

	@Override
	public void createTicket(Ticket t) {
		Client client =  clientRepo.getById(t.getClient().getId());
		List<Ticket> tickets = client.getTickets();
		tickets.add(t);
		ticketRepo.save(t);
		client.setTickets(tickets);
		clientRepo.save(client);
		
		
	}

	@Override
	public void addClient(Client c) {
		Role r = roleRepository.findByNom("USER");
		if (r == null)
			r = new Role("USER");
			roleRepository.save(r);
		c.setRoles(Arrays.asList(r));
		
		
		c.setRoles(Arrays.asList(r));
		c.setPassword(passwordEncoder.encode(c.getPassword()));
		
		clientRepo.save(c);
		
	}

	@Override
	public List<Client> getClients() {
		return clientRepo.findAll();
	}

	@Override
	public Client findById(int idClient) {
		// TODO Auto-generated method stub
		return clientRepo.getById(idClient);
	}
}
