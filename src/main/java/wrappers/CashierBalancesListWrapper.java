package wrappers;

import java.util.List;

public class CashierBalancesListWrapper {

    private List<CashierBalanceWrapper> cashierBalanceWrappers;

    public CashierBalancesListWrapper () {}

    public CashierBalancesListWrapper(List<CashierBalanceWrapper> cashierBalanceWrapper) {
        this.cashierBalanceWrappers = cashierBalanceWrapper;
    }

    public List<CashierBalanceWrapper> getCashierBalanceWrappers() {
        return cashierBalanceWrappers;
    }

    public void setCashierBalanceWrappers(List<CashierBalanceWrapper> cashierBalanceWrappers) {
        this.cashierBalanceWrappers = cashierBalanceWrappers;
    }
}