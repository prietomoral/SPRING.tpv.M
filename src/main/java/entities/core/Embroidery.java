package entities.core;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Embroidery extends Product {
    private int stitches;

    private int colors;

    private int squareMillimeters;

    public Embroidery() {
    }

    public Embroidery(long id, String reference, BigDecimal retailPrice, String description, int stitches, int colors,
            int squareMillimeters) {
        super(id, reference, retailPrice, description);
        this.stitches = stitches;
        this.colors = colors;
        this.squareMillimeters = squareMillimeters;
    }

    public int getStitches() {
        return stitches;
    }

    public int getColors() {
        return colors;
    }

    public int getSquareMillimeters() {
        return squareMillimeters;
    }

    @Override
    public String toString() {
        return "Embroidery [" + super.toString() + "stitches=" + stitches + ", colors=" + colors + ", mm2=" + squareMillimeters + "]";
    }

}
