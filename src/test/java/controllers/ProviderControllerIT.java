package controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.ProviderIdCompanyWrapper;
import wrappers.ProviderWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ProviderControllerIT {
	
	@Autowired
    private ProviderController providerController;
	
	@Test
    public void testGetAllProviders() {
        List<ProviderWrapper> providers = providerController.getAll();
        assertEquals(4, providers.size());
        assertEquals("company0", providers.get(0).getCompany());
    }  
	
	@Test
    public void testGetIdCompanyProviders() {
        List<ProviderIdCompanyWrapper> providers = providerController.getAllIdCompany();
        assertEquals(4, providers.size());
    }
}
