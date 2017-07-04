package controllers;

import api.exceptions.AlreadyExistCashierBalanceException;
import api.exceptions.NotFoundCashierBalanceException;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.text.ParseException;
import java.util.List;

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
        CashierBalance cashierBalance = cashierBalanceDao.findOne(id);

        if (cashierBalance == null) {
            throw new NotFoundCashierBalanceException();
        }

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
}
