package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public ProviderWrapper getOneById(@PathVariable(value = "id") int id) {
        return this.providerController.getOneById(id);
    }
}
