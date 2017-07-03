package api;

import api.exceptions.AlreadyExistCashierBalanceException;
import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.NotFoundCashierBalancesException;
import controllers.CashierBalanceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

import java.text.ParseException;

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
    public CashierBalancesListWrapper findAllCashierBalances() {
        return cashierBalanceController.findAllCashierBalances();
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public CashierBalanceWrapper findCashierBalanceById(@PathVariable(value = "id") int id) throws NotFoundCashierBalanceException {
        return cashierBalanceController.findCashierBalanceById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public void createCashierBalance(@RequestBody CashierBalanceWrapper cashierBalanceWrapper) throws ParseException, AlreadyExistCashierBalanceException {
        cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
    }

}
