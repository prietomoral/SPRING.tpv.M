package wrappers;

import java.math.BigDecimal;

public class CrudArticleWrapper extends ProductWrapper {

    private int stock;
    
    private int providerId;
    
    private BigDecimal wholesalePrice;

    public CrudArticleWrapper() {

    }
    
    public CrudArticleWrapper(long id, String reference, BigDecimal retailPrice, String description, BigDecimal wholesalePrice, int providerId) {
    	super(id, reference, description, retailPrice);
        this.stock = 0;
        this.wholesalePrice = wholesalePrice;
        this.providerId = providerId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public int getProviderId() {
        return providerId;
    }
    
    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }
    
    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setProvider(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    @Override
    public String toString() {
        return "CrudArticleWrapper [" + super.toString() + ", stock=" + stock + ", provider_id=" + providerId + ", wholesalePrice=" + wholesalePrice + "]";
    }

}
