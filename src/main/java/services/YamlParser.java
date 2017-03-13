package services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import entities.core.TextilePrinting;

@Component
public class YamlParser {

    @Bean
    public Yaml parser() {
        Constructor constructor = new Constructor(TextilePrintingList.class);
        TypeDescription textilePrintingDesc = new TypeDescription(TextilePrintingList.class);
        textilePrintingDesc.putListPropertyType("textilePrintingList", TextilePrinting.class);
        constructor.addTypeDescription(textilePrintingDesc);
        Yaml yamlParser = new Yaml(constructor);
        return yamlParser;
    }
}
