package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Alert;

public interface AlertDao extends JpaRepository<Alert, Integer> {

    @Query("SELECT product.id, product.description, product.stock, alert.warning, alert.critical "
            + "FROM alert INNER JOIN product ON product.id = alert.article_id " + "WHERE product.stock<=alert.warning "
            + "OR product.stock<=alert.critical ")
    public List<Alert> findAlertsWarningCritical();

}
