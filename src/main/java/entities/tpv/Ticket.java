package entities.tpv;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import entities.users.User;

@Entity
public class Ticket {

	@Id
	private long id;
    private Calendar date;
    private TicketState ticketState;

    @OneToMany
    private List<Shopping> shoppingList;
    
	@ManyToOne
	private User user;

}
