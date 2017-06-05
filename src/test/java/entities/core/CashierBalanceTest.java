package entities.core;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CashierBalanceTest {

    @Test
    public void testCashierBalance() {
        CashierBalance cBalance = new CashierBalance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        assertEquals(dateFormat.format(Calendar.getInstance().getTime()), dateFormat.format(cBalance.getDay().getTime()));
    }

    @Test
    public void testCashierBalanceConstructor() {
        Calendar day = Calendar.getInstance();
        BigDecimal totalCard = new BigDecimal(300);
        BigDecimal totalCash = new BigDecimal(100);
        BigDecimal totalChange = new BigDecimal(50);
        BigDecimal totalCheck = new BigDecimal(40);
        BigDecimal totalSales = new BigDecimal(910);
        BigDecimal expectedBalance = new BigDecimal(420);
        CashierBalance cashierBalance = new CashierBalance(day, totalCard, totalCash, totalChange, totalCheck, totalSales);

        assertEquals(totalCard, cashierBalance.getTotalCard());
        assertEquals(totalCash, cashierBalance.getTotalCash());
        assertEquals(totalChange, cashierBalance.getTotalChange());
        assertEquals(totalCheck, cashierBalance.getTotalCheck());
        assertEquals(totalSales, cashierBalance.getTotalSales());
        assertEquals(expectedBalance, cashierBalance.getBalance());
    }

    @Test
    public void testEquals() {
        Calendar day = Calendar.getInstance();
        BigDecimal totalCard = new BigDecimal(300);
        BigDecimal totalCash = new BigDecimal(100);
        BigDecimal totalChange = new BigDecimal(50);
        BigDecimal totalCheck = new BigDecimal(40);
        BigDecimal totalSales = new BigDecimal(910);
        CashierBalance cashierBalance = new CashierBalance(day, totalCard, totalCash, totalChange, totalCheck, totalSales);
        CashierBalance cashierBalanceNew = new CashierBalance(day, totalCard, totalCash, totalChange, totalCheck, totalSales);
        assertTrue(cashierBalance.equals(cashierBalanceNew));
    }
}
