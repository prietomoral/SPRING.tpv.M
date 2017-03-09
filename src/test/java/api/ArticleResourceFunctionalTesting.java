package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import api.Uris;
import api.wrappersForTest.ArticlePageWrapper;

public class ArticleResourceFunctionalTesting {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }

    @Test
    public void testSearch() {
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "1").clazz(ArticlePageWrapper.class).get().build();
        assertNotNull(articlePage);
        assertTrue(articlePage.getNumberOfElements() > 0);
        assertEquals(8, articlePage.getTotalElements());
        assertEquals(1, articlePage.getNumber());
        assertTrue(articlePage.hasContent());
        assertEquals("article5", articlePage.getContent().get(0).getReference());
        assertEquals(2, articlePage.getTotalPages());
        assertTrue(articlePage.isLast());
        assertFalse(articlePage.isFirst());
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
