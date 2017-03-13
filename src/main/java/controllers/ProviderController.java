package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ProviderDao;
import entities.core.Provider;
import wrappers.ProviderAddWrapper;

@Controller
public class ProviderController {
    private ProviderDao providerDao;
    
    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }
    
    public void add(ProviderAddWrapper providerWrapper) {
        Provider provider = new Provider(
                providerWrapper.getCompany(), 
                providerWrapper.getAddress(), 
                providerWrapper.getMobile(), 
                providerWrapper.getPhone(),
                providerWrapper.getPaymentConditions(),
                providerWrapper.getNote());
        
        this.providerDao.save(provider);
    }
    
    public List<ProviderWrapper> getAll() {
    	List<ProviderWrapper> providerList = new ArrayList<>();
    	
    	for(Provider provider: providerDao.findAll()) {
    		providerList.add(new ProviderWrapper(
    							provider.getId(),
    							provider.getCompany(),
    							provider.getAddress(),
    							provider.getMobile(),
    							provider.getPhone(),
    							provider.getPaymentConditions(),
    							provider.getNote()));
    	}
    	
    	return providerList;
    }

}
