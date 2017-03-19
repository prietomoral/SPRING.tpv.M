package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.AlertFamily;

public interface AlertFamilyDao extends JpaRepository<AlertFamily, Integer> {

}
