package api;

import api.exceptions.NotFoundCashierBalancesException;
import controllers.CashierBalanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wrappers.CashierBalancesListWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.CASHIER_BALANCES)
public class CashierBalanceResource {

    private CashierBalanceController cashierBalanceController;

    @Autowired
    public void setCashierBalanceController(CashierBalanceController cashierBalanceController) {
        this.cashierBalanceController = cashierBalanceController;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public CashierBalancesListWrapper findAllCashierBalances() throws NotFoundCashierBalancesException {
        return cashierBalanceController.findAllCashierBalances();
    }
}
