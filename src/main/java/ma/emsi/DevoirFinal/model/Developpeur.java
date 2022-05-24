package ma.emsi.DevoirFinal.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity

@DiscriminatorValue(value="developpeur")

public class Developpeur extends User {
	
	

}
