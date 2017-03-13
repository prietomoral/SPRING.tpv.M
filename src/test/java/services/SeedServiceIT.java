package services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class SeedServiceIT {
    
    @Autowired
    SeedService seedService;
    
    @Test
    public void testTextilePrintingShouldBeParsed(){
        String textilePrintingYaml = "textilePrinting.yml";
        try {
            TextilePrintingList textilePrintingList = seedService.parseYaml(textilePrintingYaml);
            assertNotNull(textilePrintingList.getTextilePrintingList());
            assertTrue(textilePrintingList.getTextilePrintingList().size() == 2);
        } catch (IOException e) {
            fail();
        }
    }
}
