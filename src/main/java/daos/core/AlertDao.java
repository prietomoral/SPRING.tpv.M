package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Alert;

public interface AlertDao extends JpaRepository<Alert, Integer> {

}
