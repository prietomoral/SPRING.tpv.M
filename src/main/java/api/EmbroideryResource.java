package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.EmbroideryController;
import entities.core.Embroidery;

@RestController
@RequestMapping(Uris.VERSION + Uris.EMBROIDERIES)
public class EmbroideryResource {

    private EmbroideryController embroideryController;

    @Autowired
    public void setEmbroideryController(EmbroideryController embroideryController) {
        this.embroideryController = embroideryController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAllEmbroidery() {
       // return embroideryController.allEmbroidery();
        return "hola";
    }

}