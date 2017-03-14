package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import api.Uris;
import api.wrappersForTest.TextilePrintingPageWrapper;

public class TextilePrintingResourceFunctionalTesting {

    private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().registerAndLoginManager();
    }

    @Test
    public void testSearchWithoutParameters() {
        TextilePrintingPageWrapper textilePrintingPage = new RestBuilder<TextilePrintingPageWrapper>(RestService.URL)
                .path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2").param("page", "1").basicAuth(token, "")
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
                .param("minRetailPrice", "20").param("maxRetailPrice", "22").param("type", "ploter").basicAuth(token, "")
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

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
