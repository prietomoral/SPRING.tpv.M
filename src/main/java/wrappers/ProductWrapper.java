package wrappers;

import java.math.BigDecimal;

import entities.core.Product;

public abstract class ProductWrapper {

    private long id;

    private String reference;

    private String description;

    private BigDecimal retailPrice;

    public ProductWrapper() {

    }

    public ProductWrapper(long id, String reference, String description, BigDecimal retailPrice) {
        this.id = id;
        this.reference = reference;
        this.description = description;
        this.retailPrice = retailPrice;
    }

    public ProductWrapper(Product product) {
        this.id = product.getId();
        this.reference = product.getReference();
        this.description = product.getDescription();
        this.retailPrice = product.getRetailPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "id=" + id + ", reference=" + reference + ", description=" + description + ", retailPrice=" + retailPrice;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id == ((ProductWrapper) obj).id;
    }

}
