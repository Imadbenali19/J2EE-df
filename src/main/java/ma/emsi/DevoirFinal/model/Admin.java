package ma.emsi.DevoirFinal.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity

@DiscriminatorValue(value="admin")

public class Admin extends User {
	

}
