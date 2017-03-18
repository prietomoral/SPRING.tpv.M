package services;

import static config.ResourceNames.YAML_FILES_ROOT;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import config.ResourceNames;
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
import entities.users.Authorization;
import entities.users.Role;
import entities.users.User;

@Service
@Transactional
@PropertySource(ResourceNames.PROPERTIES)
public class SeedService {

    private String adminUsername;

    private String adminEmail;

    private String adminPassword;

    private long adminMobile;

    @Autowired
    private Yaml yamlParser;

    @Autowired
    private ApplicationContext appContext;

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

    @PostConstruct
    public void readAdmin() {
        Environment environment = appContext.getEnvironment();
        adminMobile = Long.valueOf(environment.getProperty("admin.mobile"));
        adminUsername = environment.getProperty("admin.username");
        adminEmail = environment.getProperty("admin.email");
        adminPassword = environment.getProperty("admin.password");
        createDefaultAdmin();
    }

    public void createDefaultAdmin() {
        User adminSaved = userDao.findByMobile(adminMobile);
        if (adminSaved == null) {
            User admin = new User(adminMobile, adminUsername, adminPassword);
            admin.setEmail(adminEmail);
            userDao.save(admin);
            authorizationDao.save(new Authorization(admin, Role.ADMIN));
        }
    }

    public void parseYaml(String fileName) {
        assert fileName != null && !fileName.isEmpty();
        Resource resource = appContext.getResource(YAML_FILES_ROOT + fileName);
        InputStream input;
        try {
            input = resource.getInputStream();
            TpvGraph tpvGraph = (TpvGraph) yamlParser.load(input);

            userDao.save(tpvGraph.getUserList());
            authorizationDao.save(tpvGraph.getAuthorizationList());
            tokenDao.save(tpvGraph.getTokenList());
            voucherDao.save(tpvGraph.getVoucherList());
            providerDao.save(tpvGraph.getProviderList());
            articleDao.save(tpvGraph.getArticleList());
            embroideryDao.save(tpvGraph.getEmbroideryList());
            textilePrintingDao.save(tpvGraph.getTextilePrintingList());
            ticketDao.save(tpvGraph.getTicketList());
            invoiceDao.save(tpvGraph.getInvoiceList());
            alertDao.save(tpvGraph.getAlertList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
