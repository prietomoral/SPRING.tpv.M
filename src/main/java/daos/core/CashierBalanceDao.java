package daos.core;

import entities.core.CashierBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierBalanceDao extends JpaRepository<CashierBalance, Integer> {

}
