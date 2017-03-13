package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.ArticleWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ArticleControllerIT {

    @Autowired
    private ArticleController articleController;

    @Test
    public void testSearchWithoutParameters() {
        Page<ArticleWrapper> articlePage = articleController.search(new PageRequest(1, 4, Direction.ASC, "retailPrice"), null, null, null,
                null, false);
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
        Page<ArticleWrapper> articlePage = articleController.search(new PageRequest(1, 4, Direction.DESC, "retailPrice"), null, "article",
                new BigDecimal(21), new BigDecimal(26), false);
        assertNotNull(articlePage);
        assertTrue(articlePage.getNumberOfElements() > 0);
        assertEquals(5, articlePage.getTotalElements());
        assertEquals(1, articlePage.getNumber());
        assertTrue(articlePage.hasContent());
        assertEquals("article1", articlePage.getContent().get(0).getReference());
        assertEquals(2, articlePage.getTotalPages());
        assertTrue(articlePage.isLast());
        assertFalse(articlePage.isFirst());
    }

    @Test
    public void testSearchArticlesOnlyOnStock() {
        Page<ArticleWrapper> articlePage = articleController.search(new PageRequest(1, 4, Direction.ASC, "retailPrice"), null, null, null,
                null, true);
        assertNotNull(articlePage);
        assertEquals(0, articlePage.getTotalElements());
        assertFalse(articlePage.hasContent());
        assertEquals(0, articlePage.getTotalPages());
    }

}
