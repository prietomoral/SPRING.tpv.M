package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ProviderDao;
import entities.core.Provider;
import wrappers.ProviderWrapper;

@Controller
public class ProviderController {
    private ProviderDao providerDao;
    
    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }
    
    public void add(ProviderWrapper providerWrapper) {
        Provider provider = new Provider(
                providerWrapper.getCompany(), 
                providerWrapper.getAddress(), 
                providerWrapper.getMobile(), 
                providerWrapper.getPhone(),
                providerWrapper.getPaymentConditions(),
                providerWrapper.getNote());
        
        this.providerDao.save(provider);
    }

}
