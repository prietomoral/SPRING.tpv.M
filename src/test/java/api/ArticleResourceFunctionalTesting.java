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
import api.wrappersForTest.ArticlePageWrapper;

public class ArticleResourceFunctionalTesting {

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
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "1").basicAuth(token, "").clazz(ArticlePageWrapper.class).get().build();
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

    @Test
    public void testSearchArticlesWithParameters() {
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "1").param("description", "article").param("minRetailPrice", "21")
                .param("maxRetailPrice", "26").basicAuth(token, "").clazz(ArticlePageWrapper.class).get().build();
        assertNotNull(articlePage);
        assertTrue(articlePage.getNumberOfElements() > 0);
        assertEquals(5, articlePage.getTotalElements());
        assertEquals(1, articlePage.getNumber());
        assertTrue(articlePage.hasContent());
        assertEquals("article6", articlePage.getContent().get(0).getReference());
        assertEquals(2, articlePage.getTotalPages());
        assertTrue(articlePage.isLast());
        assertFalse(articlePage.isFirst());
    }

    @Test
    public void testSearchArticlesOnlyOnStock() {
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "1").param("onlyOnStock", "true").basicAuth(token, "").clazz(ArticlePageWrapper.class)
                .get().build();
        assertNotNull(articlePage);
        assertEquals(0, articlePage.getTotalElements());
        assertFalse(articlePage.hasContent());
        assertEquals(0, articlePage.getTotalPages());
    }

    @Test
    public void testSearchArticlesUnauthorizedWithoutToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH).param("size", "4").param("page", "1")
                .param("onlyOnStock", "true").clazz(ArticlePageWrapper.class).get().build();
    }

    @Test
    public void testSearchArticlesUnauthorizedWithToken() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH).param("size", "4").param("page", "1")
                .param("onlyOnStock", "true").basicAuth("1234", "").clazz(ArticlePageWrapper.class).get().build();
    }

    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
