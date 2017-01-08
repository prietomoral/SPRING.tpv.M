package entities.tpv;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article extends Product {
	private int stock;
	private BigDecimal wholesalePrice;

	@ManyToOne
	@JoinColumn
	private Provider provider;

}
