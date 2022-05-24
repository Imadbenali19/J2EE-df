package ma.emsi.DevoirFinal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class Role {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 private String nom;

	 
	 public Role() {
		}
	 
	public Role(String nom) {
		this.nom=nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	

	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + "]";
	}
	 
	 

}
