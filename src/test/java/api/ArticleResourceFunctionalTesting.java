package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import api.Uris;
import wrappers.ArticlePageWrapper;

public class ArticleResourceFunctionalTesting {

    @Test
    public void testSearch() {
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "0").clazz(ArticlePageWrapper.class).get().build();
        assertNotNull(articlePage);
        assertEquals(4, articlePage.getSize());
        if (articlePage.getNumberOfElements() > 0) {
            assertTrue(articlePage.hasContent());
            if (articlePage.getTotalElements() > 4) {
                assertTrue(articlePage.isFirst());
                assertFalse(articlePage.isLast());
            } else {
                assertEquals(1, articlePage.getTotalPages());
                assertTrue(articlePage.isFirst());
                assertTrue(articlePage.isLast());
            }
        } else {
            assertEquals(0, articlePage.getTotalPages());
            assertTrue(articlePage.isLast());
            assertTrue(articlePage.isFirst());
        }
    }
}
