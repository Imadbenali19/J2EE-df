package ma.emsi.DevoirFinal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonSerialize
@Transactional
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	
	private String descriptionTextuelle;
	
	private String urgence;
	
	private String state;
	
	private String environnementExecution;
	
	private String logiciel;
	
	
	@ManyToOne
	@JsonIgnoreProperties("tickets")
	private User developpeur;
	
	@ManyToOne
	@JsonIgnoreProperties("tickets")
	private User client;
	
	@ManyToOne
	@JsonIgnoreProperties("tickets")
	private User admin;

	public Ticket( Client client) {
		
	}
	
	public Ticket() {
	}
	
	public Ticket(String description, String urgency, String state, String environment, String logiciel, Client client) {
		super();
		this.developpeur = new Developpeur();
		this.client = new Client();
		this.admin = new Admin();
		this.descriptionTextuelle = description;
		this.urgence = urgency;
		this.state = state;
		this.environnementExecution = environment;
		this.logiciel = logiciel;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescriptionTextuelle() {
		return descriptionTextuelle;
	}

	public void setDescriptionTextuelle(String descriptionTextuelle) {
		this.descriptionTextuelle = descriptionTextuelle;
	}

	public String getUrgence() {
		return urgence;
	}

	public void setUrgence(String urgence) {
		this.urgence = urgence;
	}

	public String getEnvironnementExecution() {
		return environnementExecution;
	}

	public void setEnvironnementExecution(String environnementExecution) {
		this.environnementExecution = environnementExecution;
	}

	public String getLogiciel() {
		return logiciel;
	}

	public void setLogiciel(String logiciel) {
		this.logiciel = logiciel;
	}

	public User getDeveloppeur() {
		return developpeur;
	}

	public void setDeveloppeur(User developpeur) {
		this.developpeur = developpeur;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", descriptionTextuelle=" + descriptionTextuelle + ", urgence=" + urgence
				+ ", state=" + state + ", environnementExecution=" + environnementExecution + ", logiciel=" + logiciel
				+ ", developpeur=" + developpeur + ", client=" + client + ", admin=" + admin
				+ "]";
	}
	
	
	

}
