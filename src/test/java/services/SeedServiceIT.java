package services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Article;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class SeedServiceIT {
    
    @Autowired
    SeedService seedService;
    
    @Test
    public void testTpvGraphShouldBeParsed(){
        String tpvDatabaseYaml = "TPV_Database.yml";
        try {
            TpvGraph tpvGraph = seedService.parseYaml(tpvDatabaseYaml);
            assertNotNull(tpvGraph.getTextilePrintingList());
            assertTrue(tpvGraph.getTextilePrintingList().size() == 2);
            
            assertNotNull(tpvGraph.getProviderList());
            assertTrue(tpvGraph.getProviderList().size() == 2);
            
            List<Article> articleList = tpvGraph.getArticleList();
            assertNotNull(articleList);
            assertTrue(articleList.size() == 2);
            
            assertNotNull(articleList.get(0).getProvider());
            assertNotNull(articleList.get(1).getProvider());
        } catch (IOException e) {
            System.err.println(e);
            fail();
        }
    }
}
