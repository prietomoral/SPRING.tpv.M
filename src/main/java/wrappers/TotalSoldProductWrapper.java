package wrappers;

public class TotalSoldProductWrapper {
    private Long idProduct;

    private Long totalAmountSold;

    private String description;

    public TotalSoldProductWrapper() {
    }

    public TotalSoldProductWrapper(Long idProduct, Long totalAmountSold, String description) {
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
        return "TotalSoldProductWrapper [idProduct=" + idProduct + ", totalAmountSold=" + totalAmountSold + ", description=" + description
                + "]";
    }

}
