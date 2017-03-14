package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ProviderDao;
import entities.core.Provider;
import wrappers.ProviderAddWrapper;
import wrappers.ProviderIdCompanyWrapper;
import wrappers.ProviderWrapper;

@Controller
public class ProviderController {
    private ProviderDao providerDao;
    
    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }
    
    public Provider add(ProviderAddWrapper providerWrapper) {
        Provider provider = new Provider(
                providerWrapper.getCompany(), 
                providerWrapper.getAddress(), 
                providerWrapper.getMobile(), 
                providerWrapper.getPhone(),
                providerWrapper.getPaymentConditions(),
                providerWrapper.getNote());
        
        return this.providerDao.save(provider);
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
    
    public List<ProviderIdCompanyWrapper> getAllIdCompany() {
    	List<ProviderIdCompanyWrapper> providerList = new ArrayList<>();
    	
    	for(Provider provider: providerDao.findAll()) {
    		providerList.add(new ProviderIdCompanyWrapper(provider.getId(), provider.getCompany()));
    	}
    	
    	return providerList;
    }
    
    public ProviderWrapper getOneById(int id) {
    	Provider provider = providerDao.findOne(id);
    	return new ProviderWrapper(
				provider.getId(),
				provider.getCompany(),
				provider.getAddress(),
				provider.getMobile(),
				provider.getPhone(),
				provider.getPaymentConditions(),
				provider.getNote());
    }
}
