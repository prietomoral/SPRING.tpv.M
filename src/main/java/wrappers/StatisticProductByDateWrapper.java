package wrappers;

public class StatisticProductByDateWrapper {
    private int idShopping;

    private Long idProduct;

    private String description;

    private int amount;

    public StatisticProductByDateWrapper() {

    }

    public StatisticProductByDateWrapper(int idShopping, Long idProduct, String description, int amount) {
        this.idShopping = idShopping;
        this.idProduct = idProduct;
        this.description = description;
        this.amount = amount;
    }

    public int getIdShopping() {
        return idShopping;
    }

    public void setIdShopping(int idShopping) {
        this.idShopping = idShopping;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StatisticProductByDateWrapper [idShopping=" + idShopping + ", idProduct=" + idProduct + ", description=" + description
                + ", amount=" + amount + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
        result = prime * result + idShopping;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatisticProductByDateWrapper other = (StatisticProductByDateWrapper) obj;
        if (amount != other.amount)
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (idProduct == null) {
            if (other.idProduct != null)
                return false;
        } else if (!idProduct.equals(other.idProduct))
            return false;
        if (idShopping != other.idShopping)
            return false;
        return true;
    }

    
}
