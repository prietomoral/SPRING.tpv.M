package api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {
    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }
    
    @Test
    public void testCreateProvider() {
        ProviderWrapper provider = new ProviderWrapper();
        provider.setCompany("Company");
        provider.setAddress("Address");
        provider.setMobile(666666666);
        provider.setPhone(999999999);
        provider.setPaymentConditions("Payment conditions");
        provider.setNote("Note");

        for (int i = 0; i < 4; i++) {
            new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(provider)
                    .post().build();
        }
        
        
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
