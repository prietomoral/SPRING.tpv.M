package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundAlertFamilyIdException;
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

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public AlertFamilyWrapper OneAlertFamily(@PathVariable(value = "id") int id) throws NotFoundAlertFamilyIdException {
        return alertFamilyController.findOneAlertFamily(id);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) throws NotFoundAlertFamilyIdException {
        alertFamilyController.delete(id);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public void edit(@PathVariable(value = "id") int id, @RequestBody AlertFamilyWrapperCreate alertFamilyWrapperCreate)
            throws NotFoundAlertFamilyIdException {
        alertFamilyController.edit(id, alertFamilyWrapperCreate);
    }

}
