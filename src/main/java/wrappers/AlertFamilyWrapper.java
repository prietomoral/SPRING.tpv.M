package wrappers;

import java.util.List;

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
