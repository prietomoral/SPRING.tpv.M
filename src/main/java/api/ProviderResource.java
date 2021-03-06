package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundProviderIdException;
import controllers.ProviderController;
import wrappers.ProviderAddWrapper;
import wrappers.ProviderIdCompanyWrapper;
import wrappers.ProviderWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.PROVIDERS)
public class ProviderResource {
    private ProviderController providerController;
    
    @Autowired
    public void setProviderController(ProviderController providerController) {
        this.providerController = providerController;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody ProviderAddWrapper providerWrapper) {
        this.providerController.add(providerWrapper);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ProviderWrapper> getAll() {
        return this.providerController.getAll();
    }
    
    @RequestMapping(value = Uris.PROVIDERS_ID_COMPANY, method = RequestMethod.GET)
    public List<ProviderIdCompanyWrapper> getAllIdCompany() {
        return this.providerController.getAllIdCompany();
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public ProviderWrapper getOneById(@PathVariable(value = "id") int id) throws NotFoundProviderIdException {
        return this.providerController.getOneById(id);
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        this.providerController.delete(id);
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        this.providerController.deleteAll();
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody ProviderWrapper providerWrapper) {
        this.providerController.update(providerWrapper);
    }
}
