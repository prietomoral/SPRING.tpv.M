package entities.tpv;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Product {

	@Id
	private long id;
	private String reference;
	private BigDecimal retailPrice;
	private String description;

}