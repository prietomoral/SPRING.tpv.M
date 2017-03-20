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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
        result = prime * result + ((totalAmountSold == null) ? 0 : totalAmountSold.hashCode());
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
        TotalSoldProductWrapper other = (TotalSoldProductWrapper) obj;
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
        if (totalAmountSold == null) {
            if (other.totalAmountSold != null)
                return false;
        } else if (!totalAmountSold.equals(other.totalAmountSold))
            return false;
        return true;
    }
    
    

}
