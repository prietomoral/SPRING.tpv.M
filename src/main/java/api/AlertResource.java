package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlertNullValuesAreNotAllowedException;
import api.exceptions.MissingArticleIdException;
import api.exceptions.NotFoundAlertIdException;
import controllers.AlertController;
import wrappers.AlertWrapper;
import wrappers.AlertWrapperCreate;
import wrappers.AlertWrapperWarningCritical;

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

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<AlertWrapperWarningCritical> getAlertWarningCritical() {
        return alertController.findAlertWarningCritical();
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public AlertWrapper OneAlert(@PathVariable(value = "id") int id) throws NotFoundAlertIdException {
        return alertController.findOneAlert(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AlertWrapper createAlert(@RequestBody AlertWrapperCreate alertWrapperCreate)
            throws MissingArticleIdException, AlertNullValuesAreNotAllowedException {
        return alertController.createAlert(alertWrapperCreate);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) throws NotFoundAlertIdException {
        alertController.delete(id);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public void edit(@PathVariable(value = "id") int id, @RequestBody AlertWrapperCreate alertWrapperCreate)
            throws NotFoundAlertIdException {
        alertController.edit(id, alertWrapperCreate);
    }

}
