package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import api.Uris;
import api.wrappersForTest.TextilePrintingPageWrapper;

public class TextilePrintingResourceFunctionalTesting {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }

    @Test
    public void testSearch() {
        TextilePrintingPageWrapper textilePrintingPage = new RestBuilder<TextilePrintingPageWrapper>(RestService.URL)
                .path(Uris.TEXTILE_PRINTINGS + Uris.SEARCH).param("size", "2").param("page", "1").clazz(TextilePrintingPageWrapper.class)
                .get().build();
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

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
