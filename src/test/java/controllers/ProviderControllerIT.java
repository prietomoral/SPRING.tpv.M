package controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.RestService;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.core.Provider;
import wrappers.ProviderAddWrapper;
import wrappers.ProviderIdCompanyWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ProviderControllerIT {
	
	@Autowired
    private ProviderController providerController;
	
	@Test
    public void testGetIdCompanyProviders() {
        List<ProviderIdCompanyWrapper> providers = providerController.getAllIdCompany();
        assertEquals(4, providers.size());
    }  
	
	@Test
	public void testCreateProvider() {
		Provider p = providerController.add(new ProviderAddWrapper("Company", "Address", 666, 999, "Payment conditions", "Note"));
		assertEquals("Company", p.getCompany());
	}
	
	@After
	public void resetData() {
		new RestService().deleteAll();
		new RestService().populate();
	}
}
