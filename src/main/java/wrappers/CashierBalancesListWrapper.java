package wrappers;

import entities.core.CashierBalance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CashierBalancesListWrapper extends ArrayList<CashierBalanceWrapper> {

    private static final long serialVersionUID = 1L;

    public void wrapCashierBalances(List<CashierBalance> cashierBalances) {
       this.addAll(
               cashierBalances.stream()
                       .map(cashierBalance -> cashierBalanceToWrapper(cashierBalance))
                       .collect(Collectors.toList()));
    }

    public CashierBalanceWrapper cashierBalanceToWrapper(CashierBalance cashierBalance) {
        return new CashierBalanceWrapper(cashierBalance.getTotalCard(),
                cashierBalance.getTotalCash(), cashierBalance.getTotalChange(),
                cashierBalance.getTotalCheck(), cashierBalance.getTotalSales(),
                cashierBalance.getBalance(), cashierBalance.getDay());
    }
}