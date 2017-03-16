package api;

import static org.junit.Assert.*;

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
import api.wrappersForTest.ArticlePageWrapper;
import entities.core.Provider;
import wrappers.CrudArticleWrapper;

public class ArticleResourceFunctionalTesting {

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
        ArticlePageWrapper articlePage = new RestBuilder<ArticlePageWrapper>(RestService.URL).path(Uris.ARTICLES + Uris.SEARCH)
                .param("size", "4").param("page", "1").basicAuth(tokenManager, "").clazz(ArticlePageWrapper.class).get().build();
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
                .param("maxRetailPrice", "26").basicAuth(tokenManager, "").clazz(ArticlePageWrapper.class).get().build();
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
                .param("size", "4").param("page", "1").param("onlyOnStock", "true").basicAuth(tokenManager, "")
                .clazz(ArticlePageWrapper.class).get().build();
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


    @Test
    public void testCreateAndDeleteArticle() {
        Provider provider = new Provider("company", "address", 676100000, 916661000, "No", "No");
        CrudArticleWrapper article = new CrudArticleWrapper(86000001111L, "article", new BigDecimal(20), "article", new BigDecimal(10), provider.getId());
        
        new RestBuilder<Object>(RestService.URL).path(Uris.ARTICLES).body(article).basicAuth(tokenManager, "").post().build();
        new RestBuilder<Object>(RestService.URL).path(Uris.ARTICLES + '/' + article.getId()).basicAuth(tokenManager, "").delete().build();
    }
    
    @Test
    public void testArticleNotFound(){
        try{
        	new RestBuilder<Object>(RestService.URL).path(Uris.ARTICLES+ '/' + "100").basicAuth(tokenManager, "").get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testArticleNotFound (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testCreateArticleForbidden() {
        try {
            String token = new RestService().loginAdmin();
            
            Provider provider = new Provider("company", "address", 666100000, 916661000, "No", "No");
            CrudArticleWrapper article = new CrudArticleWrapper(84000001111L, "article", new BigDecimal(20), "article", new BigDecimal(10), provider.getId());
            
            new RestBuilder<Object>(RestService.URL).path(Uris.ARTICLES).body(article).basicAuth(token, "").post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.FORBIDDEN, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateArticleForbidden (" + httpError.getMessage() + "):\n " + httpError.getResponseBodyAsString());
        }
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }

}
