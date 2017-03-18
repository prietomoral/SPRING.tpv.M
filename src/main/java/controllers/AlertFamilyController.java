package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlertFamilyDao;
import entities.core.Alert;
import entities.core.AlertFamily;
import wrappers.AlertFamilyWrapper;
import wrappers.AlertWrapper;

@Controller
public class AlertFamilyController {

    private AlertFamilyDao alertFamilyDao;

    @Autowired
    public void setAlertFamilyDao(AlertFamilyDao alertFamilyDao) {
        this.alertFamilyDao = alertFamilyDao;
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

}
