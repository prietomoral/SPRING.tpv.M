package entities.tpv;

import javax.persistence.Entity;

@Entity
public class Embroidery extends Product {
	private int stitches;
	private int colors;
	private int squareMillimeters;
}
