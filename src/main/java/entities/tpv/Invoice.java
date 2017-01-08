package entities.tpv;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Invoice {
	
	@Id
	private int id;
	
	@OneToOne
	private Ticket ticket;
	

}
