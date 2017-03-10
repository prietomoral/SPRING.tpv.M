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
import wrappers.EmbroideryWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class EmbroideryControllerIT {

    @Autowired
    private EmbroideryController embroideryController;

    @Test
    public void testSearch() {
        Page<EmbroideryWrapper> embroideryPage = embroideryController.search(new PageRequest(1, 2));
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

}
