package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlertDao;
import entities.core.Alert;
import wrappers.AlertWrapper;

@Controller
public class AlertController {

	private AlertDao alertDao;

	@Autowired
	public void setAlertDao(AlertDao alertDao) {
		this.alertDao = alertDao;
	}

	public List<AlertWrapper> findAll() {
		List<AlertWrapper> alerts = new ArrayList<AlertWrapper>();
		for (Alert alert : alertDao.findAll()) {
			AlertWrapper alertWrapper = new AlertWrapper();
			alertWrapper.setId(alert.getId());
			alertWrapper.setWarning(alert.getWarning());
			alertWrapper.setCritical(alert.getCritical());
			alertWrapper.setProductDescription(alert.getArticle().getDescription());
			alerts.add(alertWrapper);
		}
		return alerts;
	}

	public AlertWrapper findOneAlert(Integer id) {

		Alert alert = alertDao.findOne(id);
		return new AlertWrapper(alert.getId(), alert.getWarning(), alert.getCritical(),
				alert.getArticle().getDescription());

	}

}
