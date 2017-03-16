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
import api.wrappersForTest.TextilePrintingPageWrapper;
import wrappers.TextilePrintingWrapper;

public class TextilePrintingResourceFunctionalTesting {

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
        TextilePrintingPageWrapper textilePrintingPage = new RestBuilder<TextilePrintingPageWrapper>(RestService.URL)
                .path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2").param("page", "1").basicAuth(tokenManager, "")
                .clazz(TextilePrintingPageWrapper.class).get().build();
        assertNotNull(textilePrintingPage);
        assertTrue(textilePrintingPage.getNumberOfElements() > 0);
        assertEquals(4, textilePrintingPage.getTotalElements());
        assertEquals(1, textilePrintingPage.getNumber());
        assertTrue(textilePrintingPage.hasContent());
        assertEquals("textilePrinting2", textilePrintingPage.getContent().get(0).getReference());
        assertEquals(2, textilePrintingPage.getTotalPages());
        assertTrue(textilePrintingPage.isLast());
        assertFalse(textilePrintingPage.isFirst());
    }

    @Test
    public void testSearchWithParameters() {
        TextilePrintingPageWrapper textilePrintingPage = new RestBuilder<TextilePrintingPageWrapper>(RestService.URL)
                .path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2").param("page", "1").param("description", "textileprinting")
                .param("minRetailPrice", "20").param("maxRetailPrice", "22").param("type", "ploter").basicAuth(tokenManager, "")
                .clazz(TextilePrintingPageWrapper.class).get().build();
        assertNotNull(textilePrintingPage);
        assertTrue(textilePrintingPage.getNumberOfElements() > 0);
        assertEquals(3, textilePrintingPage.getTotalElements());
        assertEquals(1, textilePrintingPage.getNumber());
        assertTrue(textilePrintingPage.hasContent());
        assertEquals("textilePrinting2", textilePrintingPage.getContent().get(0).getReference());
        assertEquals(2, textilePrintingPage.getTotalPages());
        assertTrue(textilePrintingPage.isLast());
        assertFalse(textilePrintingPage.isFirst());
    }

    @Test
    public void testSearchTextilePrintingsUnauthorizedWithoutToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<TextilePrintingPageWrapper>(RestService.URL).path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2")
                .param("page", "1").param("description", "textileprinting").param("minRetailPrice", "20").param("maxRetailPrice", "22")
                .param("type", "ploter").clazz(TextilePrintingPageWrapper.class).get().build();
    }

    @Test
    public void testSearchTextilePrintingsUnauthorizedWithToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<TextilePrintingPageWrapper>(RestService.URL).path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2")
                .param("page", "1").param("description", "textileprinting").param("minRetailPrice", "20").param("maxRetailPrice", "22")
                .param("type", "ploter").basicAuth("1234", "").clazz(TextilePrintingPageWrapper.class).get().build();
    }

    @Test
    public void testTextilePrintingNotFound(){
        try{
        	new RestBuilder<Object>(RestService.URL).path(Uris.TEXTILE_PRINTINGS+ '/' + "100").basicAuth(tokenManager, "").get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testTextilePrintingNotFound (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateTextilePrintingForbidden() {
        try {
            String token = new RestService().loginAdmin();
            
            TextilePrintingWrapper textilePrinting = new TextilePrintingWrapper(84000002222L, "reference", "description", new BigDecimal(20), "type");
            new RestBuilder<Object>(RestService.URL).path(Uris.TEXTILE_PRINTINGS).body(textilePrinting).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateTextilePrintingForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
