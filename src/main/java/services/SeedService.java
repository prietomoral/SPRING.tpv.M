package services;

import java.io.IOException;
import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import daos.core.ArticleDao;
import daos.core.ProviderDao;
import daos.core.TextilePrintingDao;
import daos.users.AuthorizationDao;
import daos.users.UserDao;

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
    private ProviderDao providerDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;

    private static final String YAML_FILES_ROOT = "classpath:META-INF/";

    public void parseYaml(String fileName) throws IOException {
        assert fileName != null && !fileName.isEmpty();
        Resource resource = appContext.getResource(YAML_FILES_ROOT + fileName);
        InputStream input = resource.getInputStream();
        TpvGraph tpvGraph = (TpvGraph) yamlParser.load(input);

        userDao.save(tpvGraph.getUserList());
        authorizationDao.save(tpvGraph.getAuthorizationList());
        providerDao.save(tpvGraph.getProviderList());
        articleDao.save(tpvGraph.getArticleList());
        textilePrintingDao.save(tpvGraph.getTextilePrintingList());
    }

}
