package ma.emsi.DevoirFinal.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="client")

public class Client extends User {

	
	
	
}
