package api;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CashierBalanceResourceFunctionalTesting {

    private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().registerAndLoginManager();
    }

    @Test
    public void testFindAllCashierBalances() {
        CashierBalancesListWrapper cashierBalanceWrapper =
                new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                        .basicAuth(token, "").clazz(CashierBalancesListWrapper.class).get().build();

        assertEquals(2, cashierBalanceWrapper.size());
    }

    @Test
    public void testFindCashierBalanceById() {
        CashierBalanceWrapper cashierBalanceWrapper =
                new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES + "/1")
                        .basicAuth(token, "").clazz(CashierBalanceWrapper.class).get().build();

        assertEquals(new BigDecimal(400).stripTrailingZeros(), cashierBalanceWrapper.getTotalCard().stripTrailingZeros());
        assertEquals(new BigDecimal(200).stripTrailingZeros(), cashierBalanceWrapper.getTotalCash().stripTrailingZeros());
        assertEquals(new BigDecimal(150).stripTrailingZeros(), cashierBalanceWrapper.getTotalChange().stripTrailingZeros());
        assertEquals(new BigDecimal(140).stripTrailingZeros(), cashierBalanceWrapper.getTotalCheck().stripTrailingZeros());
        assertEquals(new BigDecimal(120).stripTrailingZeros(), cashierBalanceWrapper.getBalance().stripTrailingZeros());
    }

    @Test
    public void testFindCashierBalanceByIdNotFound() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES + "/1")
                .basicAuth(token, "").clazz(CashierBalanceWrapper.class).get().build();
    }
}