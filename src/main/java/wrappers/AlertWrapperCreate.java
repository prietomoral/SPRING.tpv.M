package wrappers;

public class AlertWrapperCreate {

    private int warning;

    private int critical;

    private long product_id;

    public AlertWrapperCreate() {
    }

    public AlertWrapperCreate(int warning, int critical, long product_id) {
        this.warning = warning;
        this.critical = critical;
        this.product_id = product_id;
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

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

}
