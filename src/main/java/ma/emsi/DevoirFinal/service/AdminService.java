package ma.emsi.DevoirFinal.service;

import java.util.List;

import ma.emsi.DevoirFinal.model.Admin;
import ma.emsi.DevoirFinal.model.Ticket;

public interface AdminService {

	public Admin findById(int idAdmin);
	public void addAdmin(Admin admin);
	public List<Admin> getAdmins();
	public List<Ticket> getTickets();
	
	
	public List<Ticket> getNonAssignedTickets();
	public void assignTicket(int idTicket,int idDev,int idAdmin);
}
