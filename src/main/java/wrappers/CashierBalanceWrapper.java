package wrappers;

import org.joda.time.LocalDate;

import java.math.BigDecimal;

public class CashierBalanceWrapper {
    private int id;

    private BigDecimal balance;

    private LocalDate createdDate;

    private BigDecimal totalCard;

    private BigDecimal totalCash;

    private BigDecimal totalChange;

    private BigDecimal totalCheck;

    private BigDecimal totalSales;

    public CashierBalanceWrapper() {
        super();
    }

    public CashierBalanceWrapper(int totalCard, int totalCash, int totalChange, int totalCheck, int totalSales) {
        super();
        this.totalCard = new BigDecimal(totalCard);
        this.totalCash = new BigDecimal(totalCash);
        this.totalChange = new BigDecimal(totalChange);
        this.totalCheck = new BigDecimal(totalCheck);
        this.totalSales = new BigDecimal(totalSales);
    }

    public CashierBalanceWrapper(double totalCard, double totalCash, double totalChange, double totalCheck, double totalSales) {
        super();
        this.totalCard = new BigDecimal(totalCard);
        this.totalCash = new BigDecimal(totalCash);
        this.totalChange = new BigDecimal(totalChange);
        this.totalCheck = new BigDecimal(totalCheck);
        this.totalSales = new BigDecimal(totalSales);
    }

    public CashierBalanceWrapper(BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalChange,
            BigDecimal totalCheck, BigDecimal totalSales, BigDecimal balance, LocalDate createdDate) {
        super();
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    public CashierBalanceWrapper(int id, BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalChange,
            BigDecimal totalCheck, BigDecimal totalSales, BigDecimal balance, LocalDate createdDate) {
        super();
        this.id = id;
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    public CashierBalanceWrapper(double totalCard, double totalCash, double totalChange,
            double totalCheck, double totalSales, LocalDate createdDate) {
        super();
        this.totalCard = new BigDecimal(totalCard);
        this.totalCash = new BigDecimal(totalCash);
        this.totalChange = new BigDecimal(totalChange);
        this.totalCheck = new BigDecimal(totalCheck);
        this.totalSales = new BigDecimal(totalSales);
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalCard() {
        return totalCard;
    }

    public void setTotalCard(BigDecimal totalCard) {
        this.totalCard = totalCard;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public BigDecimal getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(BigDecimal totalChange) {
        this.totalChange = totalChange;
    }

    public BigDecimal getTotalCheck() {
        return totalCheck;
    }

    public void setTotalCheck(BigDecimal totalCheck) {
        this.totalCheck = totalCheck;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}