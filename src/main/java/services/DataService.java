package services;

import daos.core.AlertDao;
import daos.core.ArticleDao;
import daos.core.CashierBalanceDao;
import daos.core.EmbroideryDao;
import daos.core.InvoiceDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;
import daos.core.TicketDao;
import daos.core.VoucherDao;
import daos.users.AuthorizationDao;
import daos.users.TokenDao;
import daos.users.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static config.ResourceNames.DEFAULT_SEED_FILE;

@Service
public class DataService {

    @Autowired
    private SeedService seedService;

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private AlertDao alertDao;

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
    private CashierBalanceDao cashierBalanceDao;

    public void deleteAllExceptAdmin() {
        invoiceDao.deleteAll();
        ticketDao.deleteAll();

        authorizationDao.deleteAll();
        tokenDao.deleteAll();
        userDao.deleteAll();

        voucherDao.deleteAll();

        alertDao.deleteAll();
        articleDao.deleteAll();
        embroideryDao.deleteAll();
        textilePrintingDao.deleteAll();
        providerDao.deleteAll();
        cashierBalanceDao.deleteAll();

        seedService.createDefaultAdmin();
    }

    public void populate() {
        populate(DEFAULT_SEED_FILE);
    }

    public void populate(String yamlFile) {
        seedService.parseYaml(yamlFile);
    }
}
