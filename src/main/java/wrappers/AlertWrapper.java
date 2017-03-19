package wrappers;

import entities.core.Alert;

public class AlertWrapper {

    public AlertWrapper(int id, int warning, int critical, String productDescription, long product_id) {
        super();
        this.id = id;
        this.warning = warning;
        this.critical = critical;
        this.productDescription = productDescription;
        this.product_id = product_id;
    }

    public AlertWrapper(Alert alert) {
        this.id = alert.getId();
        this.warning = alert.getWarning();
        this.critical = alert.getCritical();
        this.product_id = alert.getArticle().getId();
        this.productDescription = alert.getArticle().getDescription();
    }

    private int id;

    private int warning;

    private int critical;

    private String productDescription;

    private long product_id;

    public AlertWrapper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

}
