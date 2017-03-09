package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void testSearch() {
        Page<ArticleWrapper> articlePage = articleController.search(new PageRequest(1, 4));
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

}
