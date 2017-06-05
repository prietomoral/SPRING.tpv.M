package api;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import wrappers.CashierBalancesListWrapper;

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
    public void testGetAllCashierBalances() {
        CashierBalancesListWrapper cashierBalanceWrapper =
                new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCES)
                .basicAuth(token, "").clazz(CashierBalancesListWrapper.class).get().build();

        assertEquals(2, cashierBalanceWrapper.size());
    }
}