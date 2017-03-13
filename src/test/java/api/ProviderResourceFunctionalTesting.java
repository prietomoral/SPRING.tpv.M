package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

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
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
