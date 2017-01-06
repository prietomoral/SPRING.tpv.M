package entities.tpv;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import entities.users.User;

@Entity
public class Invoice {
	
	@Id
	private int id;
	
	@OneToOne
	private Ticket ticket;
	
	@ManyToOne
	private User user;

}
