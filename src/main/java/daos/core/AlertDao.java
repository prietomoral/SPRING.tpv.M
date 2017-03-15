package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Alert;

public interface AlertDao extends JpaRepository<Alert, Integer> {

	@Query("SELECT a FROM Alert a JOIN a.article p where p.stock<=a.warning or p.stock<=a.critical")
	public List<Alert> findAlertsWarningCritical();

}
