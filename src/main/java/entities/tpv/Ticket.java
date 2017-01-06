package entities.tpv;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ticket {

	@Id
	private long id;
    private Calendar date;

    @OneToMany
    private List<Shopping> shoppings;
}
