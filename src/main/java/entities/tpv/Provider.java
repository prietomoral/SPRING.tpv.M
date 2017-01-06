package entities.tpv;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Provider {

	@Id
	@GeneratedValue
	private int id;

	private String company;
	private String address;
	private long mobile;
	private String paymentConditions;
	private String note;
}
