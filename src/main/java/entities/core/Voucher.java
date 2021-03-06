package entities.core;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import entities.users.Encrypting;

@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String reference;

    private BigDecimal value;

    private Calendar created;

    private Calendar dateOfUse;

    public Voucher() {
        created = Calendar.getInstance();
        dateOfUse = null;
    }

    public Voucher(BigDecimal value) {
        this();
        setValue(value);
    }

    public Voucher(BigDecimal value, Calendar date) {
        this(value);
        created = date;
    }

    public int getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
        reference = new Encrypting().encryptInBase64UrlSafe("" + value + Long.toString(new Date().getTime()));
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

    public boolean used() {
        return dateOfUse != null;
    }

    public void consume() {
        assert dateOfUse == null;
        dateOfUse = Calendar.getInstance();
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
        return id == ((Voucher) obj).id;
    }

    @Override
    public String toString() {
        String createTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(created.getTime());
        String useTime;
        if (this.used()) {
            useTime = new SimpleDateFormat("HH:00 dd-MMM-yyyy ").format(dateOfUse.getTime());
        } else {
            useTime = "---";
        }
        return "Voucher[" + id + ": reference=" + reference + ", value=" + value + ", created=" + createTime + ", dateOfUse=" + useTime
                + "]";
    }

}
