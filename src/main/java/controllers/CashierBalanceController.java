package controllers;

import api.exceptions.AlreadyExistCashierBalanceException;
import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.UpdateInvalidCashierBalanceException;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
public class CashierBalanceController {

    private CashierBalanceDao cashierBalanceDao;

    @Autowired
    public void setCashierBalanceDao(CashierBalanceDao cashierBalanceDao) {
        this.cashierBalanceDao = cashierBalanceDao;
    }

    public CashierBalancesListWrapper findAllCashierBalances() {
        CashierBalancesListWrapper cashierBalancesWrapper = new CashierBalancesListWrapper();
		List<CashierBalance> cashierBalances = cashierBalanceDao.findAll();
        cashierBalancesWrapper.wrapCashierBalances(cashierBalances);

        return cashierBalancesWrapper;
	}

	public CashierBalanceWrapper findCashierBalanceById(int id) throws NotFoundCashierBalanceException {
        Optional<CashierBalance> cashierBalanceOpt = cashierBalanceDao.findById(id);

        if (!cashierBalanceOpt.isPresent()) {
            throw new NotFoundCashierBalanceException();
        }
        CashierBalance cashierBalance = cashierBalanceOpt.get();

        return new CashierBalanceWrapper(cashierBalance.getTotalCard(),
                cashierBalance.getTotalCash(), cashierBalance.getTotalChange(),
                cashierBalance.getTotalCheck(), cashierBalance.getTotalSales(),
                cashierBalance.getBalance(), cashierBalance.getCreatedDate());
    }

    public void createCashierBalance(CashierBalanceWrapper cashierBalanceWrapper) throws ParseException, AlreadyExistCashierBalanceException {
        if (cashierBalanceDao.findByCreatedDate(LocalDate.now()).isPresent()) {
            throw new AlreadyExistCashierBalanceException();
        }

        CashierBalance cashierBalance = new CashierBalance(
            cashierBalanceWrapper.getTotalCard(),
            cashierBalanceWrapper.getTotalCash(),
            cashierBalanceWrapper.getTotalChange(),
            cashierBalanceWrapper.getTotalCheck(),
            cashierBalanceWrapper.getTotalSales()
        );

        cashierBalanceDao.save(cashierBalance);
    }

    public void updateCashierBalance(int id, CashierBalanceWrapper cashierBalanceWrapper) throws ParseException, UpdateInvalidCashierBalanceException, NotFoundCashierBalanceException {
        Optional<CashierBalance> cashierBalanceOpt = cashierBalanceDao.findById(id);

        if (!cashierBalanceOpt.isPresent()) {
            throw new NotFoundCashierBalanceException();
        }
        CashierBalance cashierBalance = cashierBalanceOpt.get();

        if (!cashierBalance.getCreatedDate().equals(LocalDate.now())
                && !cashierBalance.getCreatedDate().equals(cashierBalanceWrapper.getCreatedDate())) {
            throw new UpdateInvalidCashierBalanceException();
        }

        cashierBalance.setTotalCard(getNewValue(cashierBalanceWrapper.getTotalSales()));
        cashierBalance.setTotalCash(getNewValue(cashierBalanceWrapper.getTotalSales()));
        cashierBalance.setTotalChange(getNewValue(cashierBalanceWrapper.getTotalSales()));
        cashierBalance.setTotalCheck(getNewValue(cashierBalanceWrapper.getTotalCheck()));
        cashierBalance.setTotalSales(getNewValue(cashierBalanceWrapper.getTotalSales()));
        cashierBalance.setBalance(cashierBalance.getBalance());

        cashierBalanceDao.save(cashierBalance);
    }

    private BigDecimal getNewValue(BigDecimal newValue) {
        return (newValue == null) ? new BigDecimal(0) : newValue;
    }
}
