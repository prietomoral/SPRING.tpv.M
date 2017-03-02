package wrappers;

import java.math.BigDecimal;
import java.util.Calendar;

public class VoucherWrapper {

    private String reference;

    private BigDecimal value;

    private Calendar created;

    private Calendar dateOfUse;

    
    public VoucherWrapper() {
    }

    public VoucherWrapper(String reference, BigDecimal value, Calendar created, Calendar dateOfUse) {
        this.reference = reference;
        this.value = value;
        this.created = created;
        this.dateOfUse = dateOfUse;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(Calendar dateOfUse) {
        this.dateOfUse = dateOfUse;
    } 
    
    
    
    
}
