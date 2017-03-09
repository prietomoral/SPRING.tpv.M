package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import entities.core.TextilePrinting;

@Service
@Transactional
public class SeedService {

    private Yaml yamlParser;

    private static final String PRODUCT_YAML = "src/main/resources/META-INF/product.yml";

    public SeedService() {
        this.yamlParser = new Yaml();
    }

    public TextilePrinting parseYaml() throws FileNotFoundException {
        InputStream input = new FileInputStream(new File(PRODUCT_YAML));
        TextilePrinting textilePrinting = (TextilePrinting) yamlParser.loadAs(input, TextilePrinting.class);
        return textilePrinting;
    }

}
