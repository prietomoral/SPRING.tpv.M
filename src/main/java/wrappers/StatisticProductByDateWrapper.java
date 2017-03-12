package wrappers;

public class StatisticProductByDateWrapper {
    private int idShopping;

    private Long idProduct;

    private String description;

    private int amount;

    public StatisticProductByDateWrapper() {

    }

    public StatisticProductByDateWrapper(int idShopping, Long idProduct, String description, int amount) {
        super();
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

}
