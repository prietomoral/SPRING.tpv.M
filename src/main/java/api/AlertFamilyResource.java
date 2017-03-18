package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AlertFamilyController;
import wrappers.AlertFamilyWrapper;
import wrappers.AlertFamilyWrapperCreate;

@RestController
@RequestMapping(Uris.VERSION + Uris.ALERTSFAMILY)
public class AlertFamilyResource {

    private AlertFamilyController alertFamilyController;

    @Autowired
    public void setAlertController(AlertFamilyController alertFamilyController) {
        this.alertFamilyController = alertFamilyController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AlertFamilyWrapper> index() {
        return alertFamilyController.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createAlert(@RequestBody AlertFamilyWrapperCreate alertFamilyWrapperCreate) {
        alertFamilyController.createAlertFamily(alertFamilyWrapperCreate);
    }

}
