package services;

import java.io.IOException;
import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import entities.core.TextilePrinting;

@Service
@Transactional
public class SeedService {

    @Autowired
    private Yaml yamlParser;

    @Autowired
    private ApplicationContext appContext;

    private static final String YAML_FILES_ROOT = "classpath:META-INF/";

    public TextilePrinting parseYaml(String fileName) throws IOException {
        Resource resource = appContext.getResource(YAML_FILES_ROOT + fileName);
        InputStream input = resource.getInputStream();
        TextilePrinting textilePrinting = (TextilePrinting) yamlParser.load(input);
        return textilePrinting;
    }

}
