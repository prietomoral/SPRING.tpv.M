package wrappers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CashierBalanceWrapper {
    public static final String dateFormat = "dd-MM-yyyy";

    private int id;

    private BigDecimal balance;

    private String dayString;

    private BigDecimal totalCard;

    private BigDecimal totalCash;

    private BigDecimal totalChange;

    private BigDecimal totalCheck;

    private BigDecimal totalSales;

    public CashierBalanceWrapper() {
        super();
    }

    public CashierBalanceWrapper(BigDecimal totalCard, BigDecimal totalCash, BigDecimal totalChange,
            BigDecimal totalCheck, BigDecimal totalSales, BigDecimal balance, Calendar dayString) {
        super();
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        this.dayString = dateFormater.format(dayString.getTime());
        this.totalCard = totalCard;
        this.totalCash = totalCash;
        this.totalChange = totalChange;
        this.totalCheck = totalCheck;
        this.totalSales = totalSales;
        this.balance = balance;
    }

    public CashierBalanceWrapper(double balance, String dayString, double totalCard,
            double totalCash, double totalChange, double totalCheck, double totalSales) {
        super();
        this.dayString = dayString;
        this.totalCard = new BigDecimal(totalCard);
        this.totalCash = new BigDecimal(totalCash);
        this.totalChange = new BigDecimal(totalChange);
        this.totalCheck = new BigDecimal(totalCheck);
        this.totalSales = new BigDecimal(totalSales);
        this.balance = new BigDecimal(balance);
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

    public Calendar getDay() throws ParseException {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateFormater.parse(this.dayString));
        return cal;
    }

    public void setDay(Calendar day) {
        SimpleDateFormat dateFormater = new SimpleDateFormat(dateFormat);
        this.dayString = dateFormater.format(day.getTime());
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
}