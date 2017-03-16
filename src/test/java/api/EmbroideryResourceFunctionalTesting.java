package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import api.Uris;
import api.wrappersForTest.EmbroideryPageWrapper;
import wrappers.EmbroideryWrapper;

public class EmbroideryResourceFunctionalTesting {

    private static String tokenManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        tokenManager = new RestService().registerAndLoginManager();
    }

    @Test
    public void testSearchWithoutParameters() {
        EmbroideryPageWrapper embroideryPage = new RestBuilder<EmbroideryPageWrapper>(RestService.URL).path(Uris.EMBROIDERIES + Uris.SEARCH)
                .param("size", "2").param("page", "1").basicAuth(tokenManager, "").clazz(EmbroideryPageWrapper.class).get().build();
        assertNotNull(embroideryPage);
        assertTrue(embroideryPage.getNumberOfElements() > 0);
        assertEquals(4, embroideryPage.getTotalElements());
        assertEquals(1, embroideryPage.getNumber());
        assertTrue(embroideryPage.hasContent());
        assertEquals("embroidery2", embroideryPage.getContent().get(0).getReference());
        assertEquals(2, embroideryPage.getTotalPages());
        assertTrue(embroideryPage.isLast());
        assertFalse(embroideryPage.isFirst());
    }

    @Test
    public void testSearchWithParameters() {
        EmbroideryPageWrapper embroideryPage = new RestBuilder<EmbroideryPageWrapper>(RestService.URL).path(Uris.EMBROIDERIES + Uris.SEARCH)
                .param("size", "2").param("page", "1").param("description", "embroidery").param("minRetailPrice", "20")
                .param("maxRetailPrice", "23").param("minStitches", "1000").param("maxStitches", "4000").param("minSquareMillimeters", "0")
                .param("maxSquareMillimeters", "400").basicAuth(tokenManager, "").clazz(EmbroideryPageWrapper.class).get().build();
        assertNotNull(embroideryPage);
        assertTrue(embroideryPage.getNumberOfElements() > 0);
        assertEquals(3, embroideryPage.getTotalElements());
        assertEquals(1, embroideryPage.getNumber());
        assertTrue(embroideryPage.hasContent());
        assertEquals("embroidery3", embroideryPage.getContent().get(0).getReference());
        assertEquals(2, embroideryPage.getTotalPages());
        assertTrue(embroideryPage.isLast());
        assertFalse(embroideryPage.isFirst());
    }

    @Test
    public void testSearchEmbroideriesUnauthorizedWithoutToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<EmbroideryPageWrapper>(RestService.URL).path(Uris.EMBROIDERIES + Uris.SEARCH).param("size", "2").param("page", "1")
                .param("description", "embroidery").param("minRetailPrice", "20").param("maxRetailPrice", "23").param("minStitches", "1000")
                .param("maxStitches", "4000").param("minSquareMillimeters", "0").param("maxSquareMillimeters", "400")
                .clazz(EmbroideryPageWrapper.class).get().build();
    }

    @Test
    public void testSearchEmbroideriesUnauthorizedWithToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<EmbroideryPageWrapper>(RestService.URL).path(Uris.EMBROIDERIES + Uris.SEARCH).param("size", "2").param("page", "1")
                .param("description", "embroidery").param("minRetailPrice", "20").param("maxRetailPrice", "23").param("minStitches", "1000")
                .param("maxStitches", "4000").param("minSquareMillimeters", "0").param("maxSquareMillimeters", "400").basicAuth("1234", "")
                .clazz(EmbroideryPageWrapper.class).get().build();
    }

    @Test
    public void testEmbroiederyNotFound(){
        try{
        	new RestBuilder<Object>(RestService.URL).path(Uris.EMBROIDERIES+ '/' + "100").basicAuth(tokenManager, "").get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testEmbroiederyNotFound (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateEmbroideryForbidden() {
        try {
            String token = new RestService().loginAdmin();
            
            EmbroideryWrapper embroidery = new EmbroideryWrapper(84000002222L, "reference", "description", new BigDecimal(20), 1000, 1, 10);
            
            new RestBuilder<Object>(RestService.URL).path(Uris.EMBROIDERIES).body(embroidery).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateEmbroideryForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
