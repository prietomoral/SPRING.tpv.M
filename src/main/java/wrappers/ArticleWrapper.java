package wrappers;

import java.math.BigDecimal;

public class ArticleWrapper extends ProductWrapper {

    private int stock;


    public ArticleWrapper() {

    }

    public ArticleWrapper(long id, String reference, String description, BigDecimal retailPrice, int stock) {
        super(id, reference, description, retailPrice);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ArticleWrapper [" + super.toString() + ", stock=" + stock + "]";
    }

}
