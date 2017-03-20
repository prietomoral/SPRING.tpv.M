package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundYamlFileException;
import controllers.SeedController;

@RestController
@RequestMapping(Uris.VERSION + Uris.SEED)
public class SeedResource {

    private SeedController seedController;

    @Autowired
    public void setSeedController(SeedController seedController) {
        this.seedController = seedController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void seedDatabase(@RequestBody String fileName) throws NotFoundYamlFileException {
        if (!seedController.yamlFileExists(fileName)) {
            throw new NotFoundYamlFileException();
        } else {
            seedController.seedDatabase(fileName);
        }
    }
}
