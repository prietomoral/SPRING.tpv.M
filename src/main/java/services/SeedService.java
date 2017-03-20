package services;

import static config.ResourceNames.ADMIN_FILE;
import static config.ResourceNames.YAML_FILES_ROOT;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

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
public class SeedService {

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
    public void createDefaultAdmin() {
        Yaml adminYaml = new Yaml();
        Resource resource = appContext.getResource(YAML_FILES_ROOT + ADMIN_FILE);
        InputStream input;
        try {
            input = resource.getInputStream();
            User admin = adminYaml.loadAs(input, User.class);

            User adminSaved = userDao.findByMobile(admin.getMobile());
            if (adminSaved == null) {
                userDao.save(admin);
                authorizationDao.save(new Authorization(admin, Role.ADMIN));
            }
        } catch (IOException e) {
            System.err.println("ERROR: File " + ADMIN_FILE + " doesn't exist or can't be opened");
            e.printStackTrace();
        }
    }

    public void parseYaml(String fileName) {
        assert fileName != null && !fileName.isEmpty();
        
        if (!fileName.equals(ADMIN_FILE)) {
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
                System.err.println("ERROR: File " + fileName + " doesn't exist or can't be opened");
                e.printStackTrace();
            }
        }
    }

    public boolean checkYamlFileExists(String fileName) {
        Resource resource = appContext.getResource(YAML_FILES_ROOT + fileName);
        return resource.exists();
    }

}
