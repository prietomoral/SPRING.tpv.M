package api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistProductException;
import controllers.EmbroideryController;
import entities.core.Embroidery;
import wrappers.EmbroideryWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.EMBROIDERIES)
public class EmbroideryResource {

    private EmbroideryController embroideryController;

    @Autowired
    public void setEmbroideryController(EmbroideryController embroideryController) {
        this.embroideryController = embroideryController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EmbroideryWrapper> findAllEmbroidery() {
        return embroideryController.allEmbroidery();
    }

    @RequestMapping(value = Uris.SEARCH, method = RequestMethod.GET)
    public Page<EmbroideryWrapper> search(Pageable pageable, @RequestParam(required = false) String reference,
            @RequestParam(required = false) String description, @RequestParam(required = false) BigDecimal minRetailPrice,
            @RequestParam(required = false) BigDecimal maxRetailPrice, @RequestParam(required = false) Integer minStitches,
            @RequestParam(required = false) Integer maxStitches, @RequestParam(required = false) Integer minColors,
            @RequestParam(required = false) Integer maxColors, @RequestParam(required = false) Integer minSquareMillimeters,
            @RequestParam(required = false) Integer maxSquareMillimeters) {
        Page<EmbroideryWrapper> page = embroideryController.search(pageable, reference, description, minRetailPrice, maxRetailPrice,
                minStitches, maxStitches, minColors, maxColors, minSquareMillimeters, maxSquareMillimeters);
        return page;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody EmbroideryWrapper embroideryWrapper) throws AlreadyExistProductException {
        this.embroideryController.add(embroideryWrapper);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody EmbroideryWrapper embroideryWrapper){
        this.embroideryController.update(embroideryWrapper);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteEmbroidery(@PathVariable(value = "id") int id) {
        embroideryController.deleteEmbroidery(id);
    }
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public Embroidery findOneEmbroidery(@PathVariable(value = "id") int id) {
       return embroideryController.findOneEmbroidery(id);    
    }

}
