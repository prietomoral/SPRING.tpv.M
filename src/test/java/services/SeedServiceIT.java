package services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.core.ArticleDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class SeedServiceIT {

    @Autowired
    private SeedService seedService;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;

    @Test
    @Ignore
    public void testTpvGraphShouldBeParsed() {
        String tpvDatabaseYaml = "TPV_Database.yml";
        try {
            long oldProvidersNum = providerDao.count();
            long oldArticlesNum = articleDao.count();
            long oldTextilePrintingsNum = textilePrintingDao.count();
            seedService.parseYaml(tpvDatabaseYaml);
            assertTrue(providerDao.count() > oldProvidersNum);
            assertTrue(articleDao.count() > oldArticlesNum);
            assertTrue(textilePrintingDao.count() > oldTextilePrintingsNum);
        } catch (IOException e) {
            System.err.println(e);
            fail();
        }
    }
}
