package entities.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shopping {
    @Id
    @GeneratedValue
    private int id;

    private int amount;

    private int discount;

    @ManyToOne
    private Product product;

    public Shopping() {
    }

    public Shopping(int amount, int discount, Product product) {
        this.amount = amount;
        this.discount = discount;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Shopping) obj).id;
    }

    @Override
    public String toString() {
        return "Shopping[" + id + ": amount=" + amount + ", discount=" + discount + ", product=" + product + "]";
    }

}
