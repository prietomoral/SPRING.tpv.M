package entities.core;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CashierBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal balance;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate createdDate;

//    @Column(fetch = FechType)
    private BigDecimal totalCard;

    private BigDecimal totalCash;

    private BigDecimal totalChange;

    private BigDecimal totalCheck;

    private BigDecimal totalSales;

    public CashierBalance() {
        createdDate = LocalDate.now();
    }

    public CashierBalance(long totalCard, long totalCash, long totalChange,
            long totalCheck, long totalSales) {
        super();
        this.totalCard = new BigDecimal(totalCard);
        this.totalCash = new BigDecimal(totalCash);
        this.totalChange = new BigDecimal(totalChange);
        this.totalCheck = new BigDecimal(totalCheck);
        this.totalSales = new BigDecimal(totalSales);
        this.balance = this.calculateBalance();
        this.createdDate = LocalDate.now();
    }

    public CashierBalance(BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalChange,
            BigDecimal totalCheck, BigDecimal totalSales) {
        super();
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = this.calculateBalance();
        this.createdDate = LocalDate.now();
    }

    public CashierBalance(BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalChange,
            BigDecimal totalCheck, BigDecimal totalSales, LocalDate createdDate) {
        super();
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = this.calculateBalance();
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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

        return (compareBigDecimalFields(totalCash.stripTrailingZeros(), other.getTotalCash().stripTrailingZeros()) &&
            compareBigDecimalFields(totalCard.stripTrailingZeros(), other.getTotalCard().stripTrailingZeros()) &&
            compareBigDecimalFields(totalChange.stripTrailingZeros(), other.getTotalChange().stripTrailingZeros()) &&
            compareBigDecimalFields(totalCheck.stripTrailingZeros(), other.getTotalCheck().stripTrailingZeros()) &&
            compareBigDecimalFields(totalSales.stripTrailingZeros(), other.getTotalSales().stripTrailingZeros()) &&
            compareBigDecimalFields(balance.stripTrailingZeros(), other.getBalance().stripTrailingZeros())) &&
            createdDate.equals(other.getCreatedDate());
    }

    @Override
    public String toString() {
        return "CashierBalance [id=" + id + ", createdDate= " + createdDate + ", balance= " + balance + ", card= " + totalCard +
                ", cash= " + totalCash + ", change= " + totalChange + ", checks= " + totalCheck +
                ", totalSales= " + totalSales + "]";
    }

    private Boolean compareBigDecimalFields(BigDecimal original, BigDecimal other) {
        if (original != null && other != null && original.equals(other)) {
            return true;
        }
        return false;
    }
}
