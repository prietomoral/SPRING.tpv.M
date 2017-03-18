package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AlertFamilyController;
import wrappers.AlertFamilyWrapper;

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

}
