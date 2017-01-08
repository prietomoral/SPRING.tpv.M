package entities.tpv;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shopping {
	@Id
	@GeneratedValue
	private int id;

	private int amount;
	
	private int discount;
	
	@ManyToOne
	private Product article;
	
}
