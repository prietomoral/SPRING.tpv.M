package entities.tpv;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Article {
	@Id
	private long id;
	private int stock;
	private BigDecimal wholesalePrice;
	private BigDecimal retailPrice;
	private String description;

	@ManyToOne
	@JoinColumn
	private Provider provider;

}
