package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

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
        String token = new RestService().loginAdmin();

        List<ProviderWrapper> response = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(4, response.size());
        assertEquals(666100000, response.get(0).getMobile());
    }

    @Test
    public void testGetIdCompanyProviders() {
        String token = new RestService().loginAdmin();

        List<ProviderIdCompanyWrapper> response = Arrays
                .asList(new RestBuilder<ProviderIdCompanyWrapper[]>(RestService.URL).path(Uris.PROVIDERS + Uris.PROVIDERS_ID_COMPANY).get()
                        .clazz(ProviderIdCompanyWrapper[].class).basicAuth(token, "").build());

        assertNotNull(response);
        assertTrue(response.size() > 0);
        assertEquals(4, response.size());
        assertEquals("company0", response.get(0).getCompany());
    }

    @Test
    public void testGetOneById() {
        String token = new RestService().loginAdmin();

        List<ProviderWrapper> providers = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        int id = providers.get(0).getId();

        ProviderWrapper response = new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS + "/" + id).get()
                .clazz(ProviderWrapper.class).basicAuth(token, "").build();

        assertEquals(providers.get(0).getCompany(), response.getCompany());
    }

    @Test
    public void testCreateProvider() {
        String token = new RestService().loginAdmin();
        ProviderAddWrapper provider = new ProviderAddWrapper();
        provider.setCompany("Company");
        provider.setAddress("Address");
        provider.setMobile(666666666);
        provider.setPhone(999999999);
        provider.setPaymentConditions("Payment conditions");
        provider.setNote("Note");

        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).basicAuth(token, "").body(provider).post().build();
    }

    @Test
    public void testCreateProviderUnauthorized() {
        try {
            ProviderAddWrapper provider = new ProviderAddWrapper();
            provider.setCompany("Company");
            provider.setAddress("Address");
            provider.setMobile(666666666);
            provider.setPhone(999999999);
            provider.setPaymentConditions("Payment conditions");
            provider.setNote("Note");

            new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(provider).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateProviderUnauthorized (" + httpError.getMessage() + "):\n     " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testUpdateProvider() {
        String token = new RestService().loginAdmin();

        List<ProviderWrapper> providersBefore = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        ProviderWrapper provider = providersBefore.get(0);
        provider.setAddress("Address");
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(provider).put().basicAuth(token, "").build();

        List<ProviderWrapper> providersAfter = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        assertEquals("Address", providersAfter.get(0).getAddress());
    }

    @Test
    public void testDeleteProvider() {
        String token = new RestService().loginAdmin();
        List<ProviderWrapper> providersBefore = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        int id = providersBefore.get(0).getId();
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS + "/" + id).delete().basicAuth(token, "").build();

        List<ProviderWrapper> providersAfter = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        assertEquals(providersBefore.size() - 1, providersAfter.size());
    }

    @Test
    public void testDeleteAllProvider() {
        String token = new RestService().loginAdmin();
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).delete().basicAuth(token, "").build();

        List<ProviderWrapper> providersAfter = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).basicAuth(token, "").build());

        assertEquals(0, providersAfter.size());
    }

    @Test
    public void testDeleteAllProviderUnauthorized() {
        try {
            new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).delete().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testDeleteAllProviderUnauthorized (" + httpError.getMessage() + "):\n     " + httpError.getResponseBodyAsString());
        }
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
