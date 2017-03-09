package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import entities.core.TextilePrinting;

public class SeedServiceTest {
    
    SeedService seedService;
    
    @Before
    public void setUp() {
        seedService = new SeedService();
    }
    
    @Test
    public void testTextilePrintingShouldBeParsed(){
        try {
            TextilePrinting product = seedService.parseYaml();
            assertTrue("test reference".equals(product.getReference()));
            BigDecimal decimal = new BigDecimal("23.55");
            assertEquals(0, decimal.compareTo(product.getRetailPrice()));
            assertTrue("test description".equals(product.getDescription()));
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}
