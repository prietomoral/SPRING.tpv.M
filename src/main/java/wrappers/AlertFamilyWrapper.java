package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.core.Alert;
import entities.core.AlertFamily;

public class AlertFamilyWrapper {

    private int id;

    private String name;

    private List<AlertWrapper> alerts;

    public AlertFamilyWrapper() {
    }

    public AlertFamilyWrapper(int id, String name, List<AlertWrapper> alerts) {
        this.id = id;
        this.name = name;
        this.alerts = alerts;
    }

    public AlertFamilyWrapper(AlertFamily alertFamily) {
        this.id = alertFamily.getId();
        this.name = alertFamily.getName();
        List<AlertWrapper> alerts = new ArrayList<AlertWrapper>();
        for (Alert alert : alertFamily.getAlerts()) {
            AlertWrapper alertWrapper = new AlertWrapper(alert);
            alerts.add(alertWrapper);
        }
        this.alerts = alerts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AlertWrapper> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertWrapper> alerts) {
        this.alerts = alerts;
    }

}
