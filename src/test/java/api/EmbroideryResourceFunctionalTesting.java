package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import api.Uris;
import api.wrappersForTest.EmbroideryPageWrapper;

public class EmbroideryResourceFunctionalTesting {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }

    @Test
    public void testSearchWithoutParameters() {
        EmbroideryPageWrapper embroideryPage = new RestBuilder<EmbroideryPageWrapper>(RestService.URL).path(Uris.EMBROIDERIES + Uris.SEARCH)
                .param("size", "2").param("page", "1").clazz(EmbroideryPageWrapper.class).get().build();
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
                .param("maxSquareMillimeters", "400").clazz(EmbroideryPageWrapper.class).get().build();
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

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
