package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.core.AlertDao;
import daos.core.ArticleDao;
import daos.core.EmbroideryDao;
import daos.core.InvoiceDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;
import daos.core.TicketDao;
import daos.core.VoucherDao;
import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import entities.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class SeedServiceIT {

    @Autowired
    private SeedService seedService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private EmbroideryDao embroideryDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private AlertDao alertDao;

    @Test
    public void testTpvTestDatabaseShouldBeParsed() {
        String tpvDatabaseYaml = "TPV_Test_Database.yml";
        long previousUserCount = userDao.count();
        long previousAuthorizationCount = authorizationDao.count();
        long previousTokenCount = tokenDao.count();
        long previousVoucherCount = voucherDao.count();
        long previousProvidersCount = providerDao.count();
        long previousArticlesCount = articleDao.count();
        long previousEmbroideryCount = embroideryDao.count();
        long previousTextilePrintingsNum = textilePrintingDao.count();
        long previousTicketCount = ticketDao.count();
        long previousInvoiceCount = invoiceDao.count();
        long previousAlertCount = alertDao.count();

        seedService.parseYaml(tpvDatabaseYaml);

        User user = userDao.findByMobile(777000000L);
        assertNotNull(user);
        assertEquals(1, userDao.count() - previousUserCount);

        assertNotNull(authorizationDao.findRoleByUser(user));
        assertEquals(1, authorizationDao.count() - previousAuthorizationCount);

        assertNotNull(tokenDao.findByUser(user));
        assertEquals(1, tokenDao.count() - previousTokenCount);

        assertEquals(3, voucherDao.count() - previousVoucherCount);

        assertEquals(1, providerDao.count() - previousProvidersCount);

        assertNotNull(articleDao.findOne(74000001111L));
        assertEquals(1, articleDao.count() - previousArticlesCount);

        assertNotNull(embroideryDao.findOne(74000002222L));
        assertEquals(1, embroideryDao.count() - previousEmbroideryCount);

        assertNotNull(textilePrintingDao.findOne(74000003333L));
        assertEquals(1, textilePrintingDao.count() - previousTextilePrintingsNum);

        assertNotNull(ticketDao.findOne(71L));
        assertNotNull(ticketDao.findOne(72L));
        assertEquals(2, ticketDao.count() - previousTicketCount);

        assertEquals(1, invoiceDao.count() - previousInvoiceCount);

        assertEquals(1, alertDao.count() - previousAlertCount);
    }

    @Test
    public void testNotAllEntitiesYaml() {
        //YAML which only contains 2 users, 1 token, 1 embroidery
        //2 textilePrintings and 1 ticket
        String notAllEntitiesYaml = "TPV_Test_Not_All_Entities.yml";
        long previousUserCount = userDao.count();
        long previousAuthorizationCount = authorizationDao.count();
        long previousTokenCount = tokenDao.count();
        long previousVoucherCount = voucherDao.count();
        long previousProvidersCount = providerDao.count();
        long previousArticlesCount = articleDao.count();
        long previousEmbroideryCount = embroideryDao.count();
        long previousTextilePrintingsNum = textilePrintingDao.count();
        long previousTicketCount = ticketDao.count();
        long previousInvoiceCount = invoiceDao.count();
        long previousAlertCount = alertDao.count();

        seedService.parseYaml(notAllEntitiesYaml);

        User user1 = userDao.findByMobile(777000001L);
        assertNotNull(user1);
        User user2 = userDao.findByMobile(777000002L);
        assertNotNull(user2);
        assertEquals(2, userDao.count() - previousUserCount);

        assertTrue(authorizationDao.findRoleByUser(user1).isEmpty());
        assertTrue(authorizationDao.findRoleByUser(user2).isEmpty());
        assertEquals(authorizationDao.count(), previousAuthorizationCount);

        assertNotNull(tokenDao.findByUser(user1));
        assertNull(tokenDao.findByUser(user2));
        assertEquals(1, tokenDao.count() - previousTokenCount);

        assertEquals(voucherDao.count(), previousVoucherCount);

        assertEquals(providerDao.count(), previousProvidersCount);

        assertEquals(articleDao.count(), previousArticlesCount);

        assertNotNull(embroideryDao.findOne(74000002223L));
        assertEquals(1, embroideryDao.count() - previousEmbroideryCount);

        assertNotNull(textilePrintingDao.findOne(74000003334L));
        assertEquals(2, textilePrintingDao.count() - previousTextilePrintingsNum);

        assertNotNull(ticketDao.findOne(73L));
        assertEquals(1, ticketDao.count() - previousTicketCount);

        assertEquals(invoiceDao.count(), previousInvoiceCount);

        assertEquals(alertDao.count(), previousAlertCount);
    }
}
