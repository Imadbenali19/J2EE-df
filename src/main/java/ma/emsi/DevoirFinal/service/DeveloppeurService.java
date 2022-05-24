package ma.emsi.DevoirFinal.service;

import java.util.List;

import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.model.Ticket;

public interface DeveloppeurService {

	public Developpeur findById(int idDev);
	public void addDev(Developpeur dev);
	public List<Developpeur> getDevs();
	
	public List<Ticket> getDevTickets(int idDev);
	public void updateTicketStatus(int idTicket,String status);
}
