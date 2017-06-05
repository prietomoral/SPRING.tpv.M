package daos.core;

import entities.core.CashierBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface CashierBalanceDao extends JpaRepository<CashierBalance, Integer> {

    CashierBalance findOneByDay(Calendar day);
}
