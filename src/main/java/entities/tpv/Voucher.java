package entities.tpv;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Voucher {

	@Id
	@GeneratedValue
	int id;
	private BigDecimal value;
	private Calendar expiration;
	private String reference;
	private Calendar dateOfUse;

}
