package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ProviderController;
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
    public void add(@RequestBody ProviderWrapper providerWrapper) {
        this.providerController.add(providerWrapper);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<ProviderWrapper> getAll() {
        return this.providerController.getAll();
    }
}
