package api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistProductException;
import controllers.TextilePrintingController;
import wrappers.TextilePrintingWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.TEXTILE_PRINTINGS)
public class TextilePrintingResource {

    private TextilePrintingController textilePrintingController;

    @Autowired
    public void setTextilePrintingController(TextilePrintingController textilePrintingController) {
        this.textilePrintingController = textilePrintingController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TextilePrintingWrapper> all() {
        return this.textilePrintingController.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody TextilePrintingWrapper textilePrintingWrapper) throws AlreadyExistProductException {
        this.textilePrintingController.add(textilePrintingWrapper);
    }

    @RequestMapping(value = Uris.SEARCH, method = RequestMethod.GET)
    public Page<TextilePrintingWrapper> search(Pageable pageable, @RequestParam(required = false) String reference,
            @RequestParam(required = false) String description, @RequestParam(required = false) BigDecimal minRetailPrice,
            @RequestParam(required = false) BigDecimal maxRetailPrice, @RequestParam(required = false) String type) {
        Page<TextilePrintingWrapper> page = textilePrintingController.search(pageable, reference, description, minRetailPrice,
                maxRetailPrice, type);
        return page;
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        this.textilePrintingController.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void edit(int id, @RequestBody TextilePrintingWrapper textilePrintingWrapper) {
        this.textilePrintingController.edit(id, textilePrintingWrapper);
    }

}
