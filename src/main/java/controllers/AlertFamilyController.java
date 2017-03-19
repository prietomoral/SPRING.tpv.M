package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.NotFoundAlertFamilyIdException;
import daos.core.AlertDao;
import daos.core.AlertFamilyDao;
import entities.core.Alert;
import entities.core.AlertFamily;
import wrappers.AlertFamilyWrapper;
import wrappers.AlertFamilyWrapperCreate;
import wrappers.AlertWrapper;

@Controller
public class AlertFamilyController {

    private AlertFamilyDao alertFamilyDao;

    private AlertDao alertDao;

    @Autowired
    public void setAlertFamilyDao(AlertFamilyDao alertFamilyDao) {
        this.alertFamilyDao = alertFamilyDao;
    }

    @Autowired
    public void setAlertDao(AlertDao alertDao) {
        this.alertDao = alertDao;
    }

    public List<AlertFamilyWrapper> findAll() {
        List<AlertFamilyWrapper> alertFamilies = new ArrayList<AlertFamilyWrapper>();
        for (AlertFamily alertFamily : alertFamilyDao.findAll()) {
            AlertFamilyWrapper alertFamilyWrapper = new AlertFamilyWrapper();
            alertFamilyWrapper.setId(alertFamily.getId());
            alertFamilyWrapper.setName(alertFamily.getName());
            List<AlertWrapper> alerts = new ArrayList<AlertWrapper>();
            for (Alert alert : alertFamily.getAlerts()) {
                AlertWrapper alertWrapper = new AlertWrapper(alert);
                alerts.add(alertWrapper);
            }
            alertFamilyWrapper.setAlerts(alerts);
            alertFamilies.add(alertFamilyWrapper);
        }
        return alertFamilies;
    }

    public void createAlertFamily(AlertFamilyWrapperCreate alertFamilyWrapper) {
        AlertFamily alertFamily = new AlertFamily();
        List<Alert> alerts = new ArrayList<Alert>();
        for (int i = 0; i < alertFamilyWrapper.getAlerts().length; i++) {
            Alert alert = alertDao.findOne(alertFamilyWrapper.getAlerts()[i]);
            alerts.add(alert);
        }
        alertFamily.setName(alertFamilyWrapper.getName());
        alertFamily.setAlerts(alerts);
        alertFamilyDao.save(alertFamily);
    }

    public AlertFamilyWrapper findOneAlertFamily(Integer id) throws NotFoundAlertFamilyIdException {
        AlertFamily alertFamily = alertFamilyDao.findOne(id);
        if (alertFamily == null) {
            throw new NotFoundAlertFamilyIdException();
        } else {
            return new AlertFamilyWrapper(alertFamily);
        }
    }

    public void delete(int id) {
        AlertFamily alertFamily = alertFamilyDao.findOne(id);
        alertFamilyDao.delete(alertFamily);
    }

    public void edit(int id, AlertFamilyWrapperCreate alertFamilyWrapperCreate) throws NotFoundAlertFamilyIdException {
        AlertFamily alertFamily = alertFamilyDao.findOne(id);
        if (alertFamily == null) {
            throw new NotFoundAlertFamilyIdException();
        } else {
            alertFamily.setName(alertFamilyWrapperCreate.getName());
            List<Alert> alerts = new ArrayList<Alert>();
            for (int i = 0; i < alertFamilyWrapperCreate.getAlerts().length; i++) {
                Alert alert = alertDao.findOne(alertFamilyWrapperCreate.getAlerts()[i]);
                alerts.add(alert);
            }
            alertFamily.setAlerts(alerts);
            alertFamilyDao.save(alertFamily);
        }
    }

}
