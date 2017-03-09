package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AlertController;
import wrappers.AlertWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ALERTS)
public class AlertResource {

    private AlertController alertController;

    @Autowired
    public void setAlertController(AlertController alertController) {
        this.alertController = alertController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AlertWrapper> index() {
        return alertController.findAll();
    }

}
