package services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Component
public class YamlParser {

    @Bean
    public Yaml parser() {
        Constructor constructor = new Constructor(TpvGraph.class);
        Yaml yamlParser = new Yaml(constructor);
        return yamlParser;
    }
}
