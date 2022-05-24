package ma.emsi.DevoirFinal.service;

import java.util.List;

import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Ticket;

public interface ClientService {

	public Client findById(int idClient);
	public void addClient(Client c);
	public List<Client> getClients();
	
	public List<Ticket> getClientTickets(int clientId);
	public void createTicket(Ticket ticket);
}
