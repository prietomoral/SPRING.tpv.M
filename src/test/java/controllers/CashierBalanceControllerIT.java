package controllers;

import api.exceptions.NotFoundCashierBalancesException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class CashierBalanceControllerIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    CashierBalanceController cashierBalanceController;

    @Autowired
    CashierBalanceDao cashierBalanceDao;

    @Test
    public void testGetVouchersSuccess() throws ParseException {
        List<CashierBalance> cashierBalances = cashierBalanceDao.findAll();
        CashierBalancesListWrapper result;
        try {
            result = cashierBalanceController.findAllCashierBalances();
            assertEquals(cashierBalances.size(), result.size());

            List<CashierBalanceWrapper> cb = result.stream().filter(cashierBalanceWrapper -> {
                try {
                    return cashierBalanceWrapper.getDay().get(Calendar.DAY_OF_MONTH) == 15;
                } catch (ParseException e) {
                    e.printStackTrace();
                    return false;
                }
            }).collect(Collectors.toList());

            assertEquals(1, cb.size());
            assertEquals(new BigDecimal(420).stripTrailingZeros(), cb.get(0).getBalance().stripTrailingZeros());
        } catch (NotFoundCashierBalancesException e) {
            e.printStackTrace();
        }
    }

}
