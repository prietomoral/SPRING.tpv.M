package wrappers;

import java.math.BigDecimal;

import entities.core.TextilePrinting;

public class TextilePrintingWrapper extends ProductWrapper {

    private String type;

    public TextilePrintingWrapper() {

    }

    public TextilePrintingWrapper(long id, String reference, String description, BigDecimal retailPrice, String type) {
        super(id, reference, description, retailPrice);
        this.type = type;
    }

    public TextilePrintingWrapper(TextilePrinting textilePrinting) {
        super(textilePrinting);
        this.type = textilePrinting.getType();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TextilePrintingWrapper [" + super.toString() + ", type=" + type + "]";
    }

}
