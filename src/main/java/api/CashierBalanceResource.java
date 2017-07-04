package api;

import api.exceptions.AlreadyExistCashierBalanceException;
import api.exceptions.NotFoundCashierBalanceException;
import api.exceptions.UpdateInvalidCashierBalanceException;
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

    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(method = RequestMethod.GET)
    public CashierBalancesListWrapper findAllCashierBalances() {
        return cashierBalanceController.findAllCashierBalances();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(value= "/today", method = RequestMethod.GET)
    public boolean existsTodayCashierBalance() {
        return cashierBalanceController.existTodayCashierBalance();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public CashierBalanceWrapper findCashierBalanceById(@PathVariable(value = "id") int id) throws NotFoundCashierBalanceException {
        return cashierBalanceController.findCashierBalanceById(id);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(method = RequestMethod.POST)
    public void createCashierBalance(@RequestBody CashierBalanceWrapper cashierBalanceWrapper) throws ParseException, AlreadyExistCashierBalanceException {
        cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public void updateCashierBalance(@PathVariable(value = "id") int id,
            @RequestBody CashierBalanceWrapper cashierBalanceWrapper) throws ParseException, UpdateInvalidCashierBalanceException, NotFoundCashierBalanceException {
        cashierBalanceController.updateCashierBalance(id, cashierBalanceWrapper);
    }


}
