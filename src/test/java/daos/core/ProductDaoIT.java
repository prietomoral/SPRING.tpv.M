package daos.core;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Article;
import entities.core.Embroidery;
import entities.core.TextilePrinting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ProductDaoIT {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private EmbroideryDao embroideryDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;

    @Test
    public void testCreateArticle() {
        assertEquals(8, articleDao.count());
    }

    @Test
    public void testCreateEmbroidery() {
        assertEquals(4, embroideryDao.count());
    }

    @Test
    public void testCreateTextilePrinting() {
        assertEquals(4, textilePrintingDao.count());
    }

    @Test
    public void testFindByIdEmbroidery() {
        assertNotNull(embroideryDao.findOne(84000002222L + 0));
    }

    @Test
    public void testFindByIdTextilePrinting() {
        assertNotNull(textilePrintingDao.findOne(84000003333L + 0));
    }

    @Test
    public void testFindByIdArticle() {
        assertNotNull(articleDao.findOne(84000001111L + 0));
    }

    @Test
    public void testSearchArticlesWithoutParameters() {
        Page<Article> articlePage = articleDao.search(new PageRequest(1, 4), null, null, null, null, false);
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
        Page<Article> articlePage = articleDao.search(new PageRequest(1, 4), null, "article", new BigDecimal(21), new BigDecimal(26),
                false);
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
        Page<Article> articlePage = articleDao.search(new PageRequest(1, 4), null, null, null, null, true);
        assertNotNull(articlePage);
        assertEquals(0, articlePage.getTotalElements());
        assertFalse(articlePage.hasContent());
        assertEquals(0, articlePage.getTotalPages());
    }

    @Test
    public void testSearchEmbroideriesWithoutParameters() {
        Page<Embroidery> embroideryPage = embroideryDao.search(new PageRequest(1, 2), null, null, null, null, null, null, null, null, null,
                null);
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
    public void testSearchEmbroideriesWithParameters() {
        Page<Embroidery> embroideryPage = embroideryDao.search(new PageRequest(1, 2), null, "embroidery", new BigDecimal(20),
                new BigDecimal(23), 1000, 4000, null, null, 0, 400);
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

    @Test
    public void testSearchTextilePrintingsWithoutParameters() {
        Page<TextilePrinting> textilePrintingPage = textilePrintingDao.search(new PageRequest(1, 2), null, null, null, null, null);
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
    public void testSearchTextilePrintingsWithParameters() {
        Page<TextilePrinting> textilePrintingPage = textilePrintingDao.search(new PageRequest(1, 2), null, "textileprinting",
                new BigDecimal(20), new BigDecimal(22), "ploter");
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

}
