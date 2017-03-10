package services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import entities.core.TextilePrinting;

@Component
public class YamlParser {

    @Bean
    public Yaml parser() {
        Constructor constructor = new Constructor(TextilePrinting.class);
        Yaml yamlParser = new Yaml(constructor);
        return yamlParser;
    }
}
