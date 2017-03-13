package services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import entities.core.Article;
import entities.core.Provider;
import entities.core.TextilePrinting;

@Component
public class YamlParser {

    @Bean
    public Yaml parser() {
        Constructor constructor = new Constructor(TpvGraph.class);
        TypeDescription textilePrintingDesc = new TypeDescription(TpvGraph.class);
        textilePrintingDesc.putListPropertyType("textilePrintingList", TextilePrinting.class);
        textilePrintingDesc.putListPropertyType("providerList", Provider.class);
        textilePrintingDesc.putListPropertyType("articleList", Article.class);
        constructor.addTypeDescription(textilePrintingDesc);
        Yaml yamlParser = new Yaml(constructor);
        return yamlParser;
    }
}
