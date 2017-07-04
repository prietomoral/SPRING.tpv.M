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
                       .map(this::cashierBalanceToWrapper)
                       .collect(Collectors.toList()));
    }

    private CashierBalanceWrapper cashierBalanceToWrapper(CashierBalance cashierBalance) {
        return new CashierBalanceWrapper(cashierBalance.getId(), cashierBalance.getTotalCard(),
                cashierBalance.getTotalCash(), cashierBalance.getTotalChange(),
                cashierBalance.getTotalCheck(), cashierBalance.getTotalSales(),
                cashierBalance.getBalance(), cashierBalance.getCreatedDate());
    }
}