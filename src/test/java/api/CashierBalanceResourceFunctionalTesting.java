package api;

import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
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

    @Before
    public void populate() {
        new RestService().deleteAll();
        new RestService().populate();
        token = new RestService().registerAndLoginManager();
    }

    @Test
    public void testFindAllCashierBalances() {
        CashierBalancesListWrapper cashierBalanceWrappers =
                new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                        .basicAuth(token, "").clazz(CashierBalancesListWrapper.class).get().build();

        assertEquals(1, cashierBalanceWrappers.size());
    }

    @Test
    public void testFindAllCashierBalancesForbidden() {
        String adminToken = new RestService().loginAdmin();
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                .basicAuth(adminToken, "").clazz(CashierBalancesListWrapper.class).get().build();
    }

    @Test
    public void testFindCashierBalanceById() {
        int id = getIdOfAnyCashierBalance();
        CashierBalanceWrapper cashierBalanceWrapper = getCashierBalanceWrapper(id);

        assertEquals(new BigDecimal(1010).stripTrailingZeros(), cashierBalanceWrapper.getTotalSales().stripTrailingZeros());
        assertEquals(new BigDecimal(400).stripTrailingZeros(), cashierBalanceWrapper.getTotalCard().stripTrailingZeros());
        assertEquals(new BigDecimal(200).stripTrailingZeros(), cashierBalanceWrapper.getTotalCash().stripTrailingZeros());
        assertEquals(new BigDecimal(150).stripTrailingZeros(), cashierBalanceWrapper.getTotalChange().stripTrailingZeros());
        assertEquals(new BigDecimal(140).stripTrailingZeros(), cashierBalanceWrapper.getTotalCheck().stripTrailingZeros());
        assertEquals(new BigDecimal(120).stripTrailingZeros(), cashierBalanceWrapper.getBalance().stripTrailingZeros());
    }

    @Test
    public void testFindCashierBalanceByIdNotFound() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES + "/2000")
                .basicAuth(token, "").clazz(CashierBalanceWrapper.class).get().build();
    }

    @Test
    public void testCreateCashierBalanceFail() {
        CashierBalanceWrapper cashierBalanceWrapper = new CashierBalanceWrapper(400, 200, 150, 140, 1010);
        thrown.expect(new HttpMatcher(HttpStatus.CONFLICT));
        new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES).clazz(CashierBalanceWrapper.class)
                .body(cashierBalanceWrapper).basicAuth(token, "").post().build();
    }

    @Test
    public void testCreateCashierBalance() {
        new RestService().deleteAll();
        token = new RestService().registerAndLoginManager();

        CashierBalanceWrapper cashierBalanceWrapper = new CashierBalanceWrapper(400, 200, 150, 140, 1010);

        new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES).clazz(CashierBalanceWrapper.class)
                .body(cashierBalanceWrapper).basicAuth(token, "").post().build();

        CashierBalancesListWrapper cashierBalanceWrappers =
                new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                        .basicAuth(token, "").clazz(CashierBalancesListWrapper.class).get().build();

        assertEquals(1, cashierBalanceWrappers.size());
    }

    @Test
    public void testUpdateCashierBalance() {
        CashierBalanceWrapper cashierBalanceWrapper = new CashierBalanceWrapper(600, 0, 150, 140, 1010, LocalDate.now());
        int id = getIdOfAnyCashierBalance();

        new RestBuilder<CashierBalanceWrapper>(RestService.URL)
                .path(Uris.CASHIER_BALANCES + "/" + String.valueOf(id))
                .clazz(CashierBalanceWrapper.class).body(cashierBalanceWrapper).basicAuth(token, "").put().build();

        CashierBalanceWrapper cashierBalanceWrapperUpdated = getCashierBalanceWrapper(id);

        assertEquals(cashierBalanceWrapper.getTotalSales().stripTrailingZeros(), cashierBalanceWrapperUpdated.getTotalSales().stripTrailingZeros());
        assertEquals(cashierBalanceWrapper.getTotalCard().stripTrailingZeros(), cashierBalanceWrapperUpdated.getTotalCard().stripTrailingZeros());
        assertEquals(cashierBalanceWrapper.getTotalCash().stripTrailingZeros(), cashierBalanceWrapperUpdated.getTotalCash().stripTrailingZeros());
        assertEquals(cashierBalanceWrapper.getTotalChange().stripTrailingZeros(), cashierBalanceWrapperUpdated.getTotalChange().stripTrailingZeros());
        assertEquals(cashierBalanceWrapper.getTotalCheck().stripTrailingZeros(), cashierBalanceWrapperUpdated.getTotalCheck().stripTrailingZeros());
        assertEquals(new BigDecimal(120).stripTrailingZeros(), cashierBalanceWrapperUpdated.getBalance().stripTrailingZeros());
    }

    @Test
    public void testUpdateCashierBalanceInvalidId() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<CashierBalanceWrapper>(RestService.URL)
            .path(Uris.CASHIER_BALANCES + "/" + String.valueOf(20000))
            .clazz(CashierBalanceWrapper.class).body(new CashierBalanceWrapper()).basicAuth(token, "").put().build();
    }

    @Test
    public void testUpdateCashierBalanceInvalidLocalDate() {
        CashierBalanceWrapper cashierBalanceWrapper = new CashierBalanceWrapper(600, 0, 150, 140, 1010, LocalDate.now().minusDays(100));
        int id = getIdOfAnyCashierBalance();

        thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
        new RestBuilder<CashierBalanceWrapper>(RestService.URL)
            .path(Uris.CASHIER_BALANCES + "/" + String.valueOf(id))
            .clazz(CashierBalanceWrapper.class).body(cashierBalanceWrapper).basicAuth(token, "").put().build();
    }

    private CashierBalanceWrapper getCashierBalanceWrapper(int id) {
        return new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES + "/" + String.valueOf(id))
                        .basicAuth(token, "").clazz(CashierBalanceWrapper.class).get().build();
    }

    private int getIdOfAnyCashierBalance() {
        CashierBalancesListWrapper cashierBalanceWrappers =
                new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                        .basicAuth(token, "").clazz(CashierBalancesListWrapper.class).get().build();

        return cashierBalanceWrappers.stream().findFirst().get().getId();
    }
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}