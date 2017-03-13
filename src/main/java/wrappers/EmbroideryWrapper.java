package wrappers;

import java.math.BigDecimal;

import entities.core.Embroidery;

public class EmbroideryWrapper extends ProductWrapper {

    private int stitches;

    private int colors;

    private int squareMillimeters;

    public EmbroideryWrapper() {

    }

    public EmbroideryWrapper(long id, String reference, String description, BigDecimal retailPrice, int stitches, int colors,
            int squareMillimeters) {
        super(id, reference, description, retailPrice);
        this.stitches = stitches;
        this.colors = colors;
        this.squareMillimeters = squareMillimeters;
    }

    public EmbroideryWrapper(Embroidery embroidery) {
        super(embroidery);
        this.stitches = embroidery.getStitches();
        this.colors = embroidery.getColors();
        this.squareMillimeters = embroidery.getSquareMillimeters();
    }

    public int getStitches() {
        return stitches;
    }

    public void setStitches(int stitches) {
        this.stitches = stitches;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public int getSquareMillimeters() {
        return squareMillimeters;
    }

    public void setSquareMillimeters(int squareMillimeters) {
        this.squareMillimeters = squareMillimeters;
    }

    @Override
    public String toString() {
        return "EmbroideryWrapper [" + super.toString() + ", stitches=" + stitches + ", colors=" + colors + ", squareMillimeters="
                + squareMillimeters + "]";
    }

}
