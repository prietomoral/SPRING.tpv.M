package controllers;

import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.NotFoundCashierBalancesException;
import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.util.List;

@Controller
public class CashierBalanceController {

    private CashierBalanceDao cashierBalanceDao;

    @Autowired
    public void setCashierBalanceDao(CashierBalanceDao cashierBalanceDao) {
        this.cashierBalanceDao = cashierBalanceDao;
    }

    public CashierBalancesListWrapper findAllCashierBalances() throws NotFoundCashierBalancesException {
		List<CashierBalance> cashierBalances = cashierBalanceDao.findAll();
        if (cashierBalances == null || cashierBalances.size() == 0) {
            throw new NotFoundCashierBalancesException();
        }

        CashierBalancesListWrapper cashierBalancesWrapper = new CashierBalancesListWrapper();
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
                cashierBalance.getBalance(), cashierBalance.getDay());
    }
}
