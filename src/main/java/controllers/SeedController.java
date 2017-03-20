package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.SeedService;

@Controller
public class SeedController {

    private SeedService seedService;

    @Autowired
    public void setSeedService(SeedService seedService) {
        this.seedService = seedService;
    }
    
    public boolean yamlFileExists(String fileName) {
        return seedService.checkYamlFileExists(fileName);
    }
    
    public void seedDatabase(String fileName) {
        seedService.parseYaml(fileName);
    }
}
