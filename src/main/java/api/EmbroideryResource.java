package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistEmbroideryException;
import controllers.EmbroideryController;
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
    public Page<EmbroideryWrapper> search(Pageable pageable) {
        Page<EmbroideryWrapper> page = embroideryController.search(pageable);
        return page;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody EmbroideryWrapper embroideryWrapper) throws AlreadyExistEmbroideryException {
        this.embroideryController.add(embroideryWrapper);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void deleteEmbroidery(@PathVariable(value = "id") int id) {
        embroideryController.deleteEmbroidery(id);
    }

}
