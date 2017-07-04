package controllers;

import api.exceptions.AlreadyExistCashierBalanceException;
import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.UpdateInvalidCashierBalanceException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.joda.time.LocalDate;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class CashierBalanceControllerIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private CashierBalanceController cashierBalanceController;

    @Autowired
    private CashierBalanceDao cashierBalanceDao;

    @Test
    public void testGetCashierBalancesSuccess() {
        List<CashierBalance> cashierBalances = cashierBalanceDao.findAll();
        CashierBalancesListWrapper result;
        result = cashierBalanceController.findAllCashierBalances();
        assertEquals(cashierBalances.size(), result.getCashierBalanceWrappers().size());
    }

    @Test
    public void existTodayCashierBalance() {
        Optional<CashierBalance> cashierBalanceOpt = cashierBalanceDao.findByCreatedDate(LocalDate.now());
        CashierBalance cashierBalance;

        if (!cashierBalanceOpt.isPresent()) {
            cashierBalance = cashierBalanceDao.save(new CashierBalance(400, 200, 150, 140, 1010));
        } else {
            cashierBalance = cashierBalanceOpt.get();
        }

        assertTrue(cashierBalanceController.existTodayCashierBalance());

        cashierBalanceDao.delete(cashierBalance.getId());
        assertFalse(cashierBalanceController.existTodayCashierBalance());
    }

    @Test
    public void testGetCashierBalanceSuccess() throws ParseException {
        try {
            CashierBalanceWrapper result = cashierBalanceController.findCashierBalanceById(1);

            assertEquals(1, result.getId());
            assertEquals(new BigDecimal(1010).stripTrailingZeros(), result.getTotalSales().stripTrailingZeros());
            assertEquals(new BigDecimal(400).stripTrailingZeros(), result.getTotalCard().stripTrailingZeros());
            assertEquals(new BigDecimal(200).stripTrailingZeros(), result.getTotalCash().stripTrailingZeros());
            assertEquals(new BigDecimal(150).stripTrailingZeros(), result.getTotalChange().stripTrailingZeros());
            assertEquals(new BigDecimal(140).stripTrailingZeros(), result.getTotalCheck().stripTrailingZeros());
            assertEquals(new BigDecimal(120).stripTrailingZeros(), result.getBalance().stripTrailingZeros());
            assertEquals(new LocalDate(), result.getCreatedDate());
        } catch (NotFoundCashierBalanceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCashierBalanceNotFound() {
        try {
            cashierBalanceController.findCashierBalanceById(200);
        } catch (NotFoundCashierBalanceException e) {
            assertEquals("No existe un Balance de Caja con ese id en el sistema. ", e.getMessage());
        }
    }

    @Test
    public void testCreateCashierBalanceSuccess() throws ParseException {
        try {
            Optional<CashierBalance> cashierBalanceOpt = cashierBalanceDao.findByCreatedDate(LocalDate.now());
            if (cashierBalanceOpt.isPresent()) {
                cashierBalanceDao.delete(cashierBalanceOpt.get().getId());
            }
            int sizeBeforeCreate = cashierBalanceDao.findAll().size();
            CashierBalanceWrapper cashierBalanceWrapper =
                    new CashierBalanceWrapper(400, 200, 150, 140, 1010);
            cashierBalanceController.createCashierBalance(cashierBalanceWrapper);

            int sizeAfterCreate = cashierBalanceDao.findAll().size();
            assertEquals(sizeBeforeCreate + 1, sizeAfterCreate);

            cashierBalanceOpt = cashierBalanceDao.findByCreatedDate(LocalDate.now());
            if (cashierBalanceOpt.isPresent()) {
                CashierBalance cashierBalanceExpected = new CashierBalance(400, 200, 150, 140, 1010);
                CashierBalance cashierBalance = cashierBalanceOpt.get();
                assertTrue(cashierBalanceExpected.equals(cashierBalance));
            }
        } catch (AlreadyExistCashierBalanceException e) {
            assertEquals("Balance de Caja ya existe para este día. ", e.getMessage());
        }
    }

    @Test
    public void testCreateCashierBalanceException() throws ParseException {
        try {
            CashierBalanceWrapper cashierBalanceWrapper =
                    new CashierBalanceWrapper(400, 200, 150, 140, 1010);

            Optional<CashierBalance> cashierBalanceOpt = cashierBalanceDao.findByCreatedDate(LocalDate.now());

            if (!cashierBalanceOpt.isPresent()) {
                cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
            }
            cashierBalanceController.createCashierBalance(cashierBalanceWrapper);

        } catch (AlreadyExistCashierBalanceException e) {
            assertEquals("Balance de Caja ya existe para este día. ", e.getMessage());
        }
    }

    @Test
    public void testUpdateCashierBalance() throws ParseException {
        try {
            CashierBalance cashierBalance = cashierBalanceDao.findAll().stream().findFirst().get();

            CashierBalanceWrapper cashierBalanceWrapper =
                    new CashierBalanceWrapper(600, 0, 150, 140, 1010);
            CashierBalance cashierBalanceExpected =
                    new CashierBalance(600, 0, 150, 140, 1010);

            cashierBalanceController.updateCashierBalance(cashierBalance.getId(), cashierBalanceWrapper);

            cashierBalance = cashierBalanceDao.findOne(cashierBalance.getId());
            assertTrue(cashierBalanceExpected.equals(cashierBalance));

        } catch (NotFoundCashierBalanceException e) {
            assertEquals("No existe un Balance de Caja con ese id en el sistema. ", e.getMessage());
        } catch (UpdateInvalidCashierBalanceException e) {
            assertEquals("Sólo puede modificar el Balance de Caja del día actual. ", e.getMessage());
        }
    }

    @Test
    public void testUpdateCashierBalanceInvalidId() throws ParseException {
        try {
            CashierBalanceWrapper cashierBalanceWrapper =
                    new CashierBalanceWrapper(600, 0, 150, 140, 1010);
            cashierBalanceController.updateCashierBalance(200, cashierBalanceWrapper);

        } catch (NotFoundCashierBalanceException e) {
            assertEquals("No existe un Balance de Caja con ese id en el sistema. ", e.getMessage());
        } catch (UpdateInvalidCashierBalanceException e) {
            assertEquals("Sólo puede modificar el Balance de Caja del día actual. ", e.getMessage());
        }
    }

    @Test
    public void testUpdateCashierBalanceInvalidLocalDate() throws ParseException {
        try {
            CashierBalance cashierBalance = cashierBalanceDao.findAll().stream().findFirst().get();

            CashierBalanceWrapper cashierBalanceWrapper =
                    new CashierBalanceWrapper(600, 0, 150, 140, 1010, LocalDate.now().minusDays(1));
            cashierBalanceController.updateCashierBalance(cashierBalance.getId(), cashierBalanceWrapper);

        } catch (NotFoundCashierBalanceException e) {
            assertEquals("No existe un Balance de Caja con ese id en el sistema. ", e.getMessage());
        } catch (UpdateInvalidCashierBalanceException e) {
            assertEquals("Sólo puede modificar el Balance de Caja del día actual. ", e.getMessage());
        }
    }
}
