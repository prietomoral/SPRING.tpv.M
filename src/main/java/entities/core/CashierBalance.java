package entities.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
public class CashierBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal balance;

    @Temporal(TemporalType.DATE)
    private Calendar day;

    private BigDecimal totalCard;

    private BigDecimal totalCash;

    private BigDecimal totalChange;

    private BigDecimal totalCheck;

    private BigDecimal totalSales;

    public CashierBalance() {
        day = Calendar.getInstance();
    }

    public CashierBalance(BigDecimal totalCard,
            BigDecimal totalCash, BigDecimal totalChange, BigDecimal totalCheck, BigDecimal totalSales) {
        super();
        day = Calendar.getInstance();
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = this.calculateBalance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(BigDecimal totalChange) {
        this.totalChange = totalChange;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
        this.balance = this.calculateBalance();
    }

    public BigDecimal getTotalCheck() {
        return totalCheck;
    }

    public void setTotalCheck(BigDecimal totalCheck) {
        this.totalCheck = totalCheck;
        this.balance = this.calculateBalance();
    }

    public BigDecimal getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(BigDecimal totalCard) {
        this.totalCard = totalCard;
        this.balance = this.calculateBalance();
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
        this.balance = this.calculateBalance();
    }

    private BigDecimal calculateBalance() {
        BigDecimal balance = this.totalSales;
        if (this.totalChange != null) {
            balance = balance.subtract(this.totalChange);
        }
        if (this.totalCash != null) {
            balance = balance.subtract(this.totalCash);
        }
        if (this.totalCheck != null) {
            balance = balance.subtract(this.totalCheck);
        }
        if (this.totalCard != null) {
            balance = balance.subtract(this.totalCard);
        }

        return balance;
    }

    @Override
    public int hashCode() {
        return this.id;
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
        CashierBalance other = (CashierBalance) obj;

        if (!(compareBigDecimalFields(totalCash, other.getTotalCash()) &&
            compareBigDecimalFields(totalCard, other.getTotalCard()) &&
            compareBigDecimalFields(totalChange, other.getTotalChange()) &&
            compareBigDecimalFields(totalCheck, other.getTotalCheck()) &&
            compareBigDecimalFields(totalSales, other.getTotalSales()) &&
            compareBigDecimalFields(balance, other.getBalance()))) {
            return false;
        }

        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;

        return true;
    }

    private Boolean compareBigDecimalFields(BigDecimal original, BigDecimal other) {
        if (original != null && other != null && original.equals(other)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CashierBalance [id=" + id + ", day= " + day + ", balance= " + balance + ", card= " + totalCard +
                ", cash= " + totalCash + ", change= " + totalChange + ", checks= " + totalCheck +
                ", totalSales= " + totalSales + "]";
    }
}
