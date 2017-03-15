package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.AlertNullValuesAreNotAllowedException;
import api.exceptions.MissingArticleIdException;
import api.exceptions.NotFoundAlertIdException;
import daos.core.AlertDao;
import daos.core.ArticleDao;
import entities.core.Alert;
import entities.core.Article;
import wrappers.AlertWrapper;
import wrappers.AlertWrapperCreate;
import wrappers.AlertWrapperWarningCritical;

@Controller
public class AlertController {

	private AlertDao alertDao;

	private ArticleDao articleDao;

	@Autowired
	public void setAlertDao(AlertDao alertDao) {
		this.alertDao = alertDao;
	}

	@Autowired
	public void setProductDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
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

	public List<AlertWrapperWarningCritical> findAlertWarningCritical() {
		List<AlertWrapperWarningCritical> alerts = new ArrayList<AlertWrapperWarningCritical>();

		for (Alert alert : alertDao.findAlertsWarningCritical()) {
			AlertWrapperWarningCritical alertWrapperWarningCritical = new AlertWrapperWarningCritical();
			alertWrapperWarningCritical.setId(alert.getId());
			alertWrapperWarningCritical.setDescripcion(alert.getArticle().getDescription());
			alertWrapperWarningCritical.setStock(alert.getArticle().getStock());
			alertWrapperWarningCritical.setWarning(alert.getWarning());
			alertWrapperWarningCritical.setCritical(alert.getCritical());
			alerts.add(alertWrapperWarningCritical);
		}
		return alerts;
	}

	public AlertWrapper findOneAlert(Integer id) throws NotFoundAlertIdException {

		Alert alert = alertDao.findOne(id);
		if (alert == null) {
			throw new NotFoundAlertIdException();
		} else {
			return new AlertWrapper(alert.getId(), alert.getWarning(), alert.getCritical(),
					alert.getArticle().getDescription(), alert.getArticle().getId());
		}

	}

	public AlertWrapper createAlert(AlertWrapperCreate alertWrapperCreate)
			throws MissingArticleIdException, AlertNullValuesAreNotAllowedException {
		Alert alert = new Alert();
		Article article = articleDao.findOne(alertWrapperCreate.getProduct_id());
		if (article == null) {
			throw new MissingArticleIdException();
		}

		alert.setArticle(article);
		alert.setCritical(alertWrapperCreate.getCritical());
		alert.setWarning(alertWrapperCreate.getWarning());
		alertDao.save(alert);
		AlertWrapper alertWrapper = new AlertWrapper(alert.getId(), alert.getWarning(), alert.getCritical(),
				article.getDescription(), article.getId());
		return alertWrapper;

	}

	public void delete(int id) {
		Alert alert = alertDao.findOne(id);
		alertDao.delete(alert);
	}

	public void edit(int id, AlertWrapperCreate alertWrapperCreate) throws NotFoundAlertIdException {
		Alert alert = alertDao.findOne(id);
		if (alert == null) {
			throw new NotFoundAlertIdException();
		} else {
			Article article = articleDao.findOne(alertWrapperCreate.getProduct_id());
			alert.setArticle(article);
			alert.setWarning(alertWrapperCreate.getWarning());
			alert.setCritical(alertWrapperCreate.getCritical());
			alertDao.save(alert);
		}
	}

}
