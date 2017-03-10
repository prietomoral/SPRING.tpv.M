package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.TextilePrinting;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class SeedServiceIT {
    
    @Autowired
    SeedService seedService;
    
    @Test
    public void testTextilePrintingShouldBeParsed(){
        String textilePrintingYaml = "textilePrinting.yml";
        try {
            TextilePrinting product = seedService.parseYaml(textilePrintingYaml);
            assertTrue("test reference".equals(product.getReference()));
            BigDecimal decimal = new BigDecimal("23.55");
            assertEquals(0, decimal.compareTo(product.getRetailPrice()));
            assertTrue("test description".equals(product.getDescription()));
        } catch (IOException e) {
            fail();
        }
    }
}
