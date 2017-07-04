package daos.core;

import entities.core.CashierBalance;
import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CashierBalanceDao extends JpaRepository<CashierBalance, Integer> {

    Optional<CashierBalance> findByCreatedDate(LocalDate createdDate);
}
