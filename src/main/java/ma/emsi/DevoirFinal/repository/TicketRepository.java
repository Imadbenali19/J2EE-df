package ma.emsi.DevoirFinal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.Admin;
import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Developpeur;
import ma.emsi.DevoirFinal.model.Ticket;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query("SELECT t FROM Ticket t WHERE t.developpeur IS NULL")
	List<Ticket> getUnassinged();
	
	
	@Modifying
	@Query("Update Ticket t set t.developpeur = :dev, t.admin = :admin  where t.id =  :idTicket")
	void assignDev( @Param("idTicket") int idTicket, @Param("dev") Developpeur d, @Param("admin") Admin a);

	
	@Query("Select t from Ticket t Where t.client = :client")
	List<Ticket> getClientTickets(Client client);
	
	
	
	@Query("Select t from Ticket t Where t.developpeur.id = :idDev")
	List<Ticket> findByDev(int idDev);
	
	@Modifying
	@Query("Update Ticket t set t.state = :state where t.id =  :idTicket")
	void updateTicketStatus(@Param("idTicket") int idTicket,@Param("state") String state);
}
