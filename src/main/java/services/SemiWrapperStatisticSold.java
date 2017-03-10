package services;

public class SemiWrapperStatisticSold {
    private Long idProduct;

    private int totalAmountSold;

    private String description;

    public SemiWrapperStatisticSold() {
    }

    public SemiWrapperStatisticSold(Long idProduct, String description, int totalAmountSold) {
        this.idProduct = idProduct;
        this.totalAmountSold = totalAmountSold;
        this.description = description;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public int getTotalAmountSold() {
        return totalAmountSold;
    }

    public void setTotalAmountSold(int totalAmountSold) {
        this.totalAmountSold = totalAmountSold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SemiWrapperStatisticSold [idProduct=" + idProduct + ", totalAmountSold=" + totalAmountSold + ", description=" + description
                + "]";
    }

    
}
