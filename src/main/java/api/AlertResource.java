package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.MissingArticleIdException;
import controllers.AlertController;
import wrappers.AlertWrapper;
import wrappers.AlertWrapperCreate;

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

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public AlertWrapper OneAlert(@PathVariable(value = "id") int id) {
        return alertController.findOneAlert(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AlertWrapper createAlert(@RequestBody AlertWrapperCreate alertWrapperCreate) throws MissingArticleIdException {
        Long product_id = alertWrapperCreate.getProduct_id();
        if (product_id.equals(null)) {
            throw new MissingArticleIdException();
        }
        return alertController.createAlert(alertWrapperCreate);
    }

}
