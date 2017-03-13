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
import entities.core.Article;
import entities.core.Provider;
import entities.core.TextilePrinting;

@Service
@Transactional
public class SeedService {

    @Autowired
    private Yaml yamlParser;

    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private ProviderDao providerDao;
    
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;

    private static final String YAML_FILES_ROOT = "classpath:META-INF/";

    public void parseYaml(String fileName) throws IOException {
        Resource resource = appContext.getResource(YAML_FILES_ROOT + fileName);
        InputStream input = resource.getInputStream();
        TpvGraph tpvGraph = (TpvGraph) yamlParser.load(input);
        
        for (Provider provider : tpvGraph.getProviderList()) {
            providerDao.save(provider);
        }
        
        for (Article article : tpvGraph.getArticleList()) {
            articleDao.save(article);
        }
        
        for (TextilePrinting textilePrinting : tpvGraph.getTextilePrintingList()) {
            textilePrintingDao.save(textilePrinting);
        }
    }

}
