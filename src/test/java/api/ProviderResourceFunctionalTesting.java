package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import wrappers.ProviderAddWrapper;
import wrappers.ProviderIdCompanyWrapper;
import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {
	@BeforeClass
    public static void populate() {
        new RestService().populate();
    }
	
	@Test
    public void testGetAllProviders() {
    	List<ProviderWrapper> response = Arrays.asList(
                new RestTemplate().exchange(RestService.URL + Uris.PROVIDERS, HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()), ProviderWrapper[].class).getBody());
        
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(4, response.size());
        assertEquals(666100000, response.get(0).getMobile());
    }
	
	@Test
    public void testGetIdCompanyProviders() {
    	List<ProviderIdCompanyWrapper> response = Arrays.asList(
                new RestTemplate().exchange(RestService.URL + Uris.PROVIDERS + Uris.PROVIDERS_ID_COMPANY, HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()), ProviderIdCompanyWrapper[].class).getBody());
        
        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(4, response.size());
        assertEquals("company0", response.get(0).getCompany());
    }
	
	@Test
	public void testGetOneById() {
		List<ProviderWrapper> providers = Arrays.asList(
                new RestTemplate().exchange(RestService.URL + Uris.PROVIDERS, HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()), ProviderWrapper[].class).getBody());
		int id = providers.get(0).getId();
		ProviderWrapper response = new RestTemplate().exchange(RestService.URL + Uris.PROVIDERS + "/" + id, HttpMethod.GET, new HttpEntity<Object>(new HttpHeaders()), ProviderWrapper.class).getBody();
		assertEquals(providers.get(0).getCompany(), response.getCompany());
	}
	
	@Test
	public void testCreateProvider() {
		ProviderAddWrapper provider = new ProviderAddWrapper();
        provider.setCompany("Company");
        provider.setAddress("Address");
        provider.setMobile(666666666);
        provider.setPhone(999999999);
        provider.setPaymentConditions("Payment conditions");
        provider.setNote("Note");

        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(provider).post().build();
	}
	
	@After
	public void resetData() {
		new RestService().deleteAll();
		new RestService().populate();
	}
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
