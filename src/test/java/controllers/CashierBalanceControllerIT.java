package controllers;

import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.NotFoundCashierBalancesException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.hibernate.Hibernate;
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
    public void testGetCashierBalancesSuccess() throws ParseException {
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

    @Test
    public void testGetCashierBalanceSuccess() throws ParseException {
        try {
            Hibernate.initialize(cashierBalanceController.findCashierBalanceById(1));

            CashierBalanceWrapper result = cashierBalanceController.findCashierBalanceById(1);

            assertEquals(new BigDecimal(400).stripTrailingZeros(), result.getTotalCard().stripTrailingZeros());
            assertEquals(new BigDecimal(200).stripTrailingZeros(), result.getTotalCash().stripTrailingZeros());
            assertEquals(new BigDecimal(150).stripTrailingZeros(), result.getTotalChange().stripTrailingZeros());
            assertEquals(new BigDecimal(140).stripTrailingZeros(), result.getTotalCheck().stripTrailingZeros());
            assertEquals(new BigDecimal(120).stripTrailingZeros(), result.getBalance().stripTrailingZeros());
            assertEquals(1489446000000L, result.getDay().getTimeInMillis());
        } catch (NotFoundCashierBalanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCashierBalanceNotFound() {
        try {
            cashierBalanceController.findCashierBalanceById(20);
        } catch (NotFoundCashierBalanceException e) {
            assertEquals("No existe un Balance de Caja con ese id en el sistema. ", e.getMessage());
        }
    }
}
