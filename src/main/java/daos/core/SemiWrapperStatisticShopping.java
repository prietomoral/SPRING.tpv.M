package daos.core;

public class SemiWrapperStatisticShopping {
    private Long idProduct;

    private Long totalAmountSold;

    private String description;

    public SemiWrapperStatisticShopping() {
    }

    public SemiWrapperStatisticShopping(Long idProduct, String description, Long totalAmountSold) {
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

    public Long getTotalAmountSold() {
        return totalAmountSold;
    }

    public void setTotalAmountSold(Long totalAmountSold) {
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
        return "SemiWrapperStatisticShopping [idProduct=" + idProduct + ", totalAmountSold=" + totalAmountSold + ", description="
                + description + "]";
    }
    
    
}
