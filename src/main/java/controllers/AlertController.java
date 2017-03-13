package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.MissingArticleIdException;
import daos.core.AlertDao;
import daos.core.ArticleDao;
import entities.core.Alert;
import entities.core.Article;
import wrappers.AlertWrapper;
import wrappers.AlertWrapperCreate;

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

    public AlertWrapper findOneAlert(Integer id) {

        Alert alert = alertDao.findOne(id);
        return new AlertWrapper(alert.getId(), alert.getWarning(), alert.getCritical(), alert.getArticle().getDescription());

    }

    public AlertWrapper createAlert(AlertWrapperCreate alertWrapperCreate) throws MissingArticleIdException {
        Alert alert = new Alert();
        Article article = articleDao.findOne(alertWrapperCreate.getProduct_id());
        if (article == null) {
            throw new MissingArticleIdException();
        } else {
            alert.setArticle(article);
            alert.setCritical(alertWrapperCreate.getCritical());
            alert.setWarning(alertWrapperCreate.getWarning());
            alertDao.save(alert);
            AlertWrapper alertWrapper = new AlertWrapper(alert.getId(), alert.getWarning(), alert.getCritical(), article.getDescription());
            return alertWrapper;
        }
    }

    public void delete(int id) {
        Alert alert = alertDao.findOne(id);
        alertDao.delete(alert);
    }

    public void edit(int id, AlertWrapperCreate alertWrapperCreate) {
        Alert alert = alertDao.findOne(id);
        Article article = articleDao.findOne(alertWrapperCreate.getProduct_id());
        alert.setArticle(article);
        alert.setWarning(alertWrapperCreate.getWarning());
        alert.setCritical(alertWrapperCreate.getCritical());
        alertDao.save(alert);
    }

}
