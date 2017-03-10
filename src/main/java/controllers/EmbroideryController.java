package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import daos.core.EmbroideryDao;
import entities.core.Embroidery;
import wrappers.EmbroideryWrapper;

@Controller
public class EmbroideryController {

    private EmbroideryDao embroideryDao;

    @Autowired
    public void setEmbroideryDao(EmbroideryDao embroideryDao) {
        this.embroideryDao = embroideryDao;
    }

    public List<EmbroideryWrapper> allEmbroidery() {

        List<EmbroideryWrapper> embroideryList = new ArrayList<EmbroideryWrapper>();

        for (Embroidery embroidery : embroideryDao.findAll()) {

            EmbroideryWrapper embroideryWrapper = new EmbroideryWrapper();

            embroideryWrapper.setReference(embroidery.getReference());
            embroideryWrapper.setDescription(embroidery.getDescription());
            embroideryWrapper.setRetailPrice(embroidery.getRetailPrice());
            embroideryWrapper.setStitches(embroidery.getStitches());
            embroideryWrapper.setColors(embroidery.getColors());
            embroideryWrapper.setSquareMillimeters(embroidery.getSquareMillimeters());

            embroideryList.add(embroideryWrapper);
        }
        return embroideryList;

    }

    public Page<EmbroideryWrapper> search(Pageable pageable) {
        Page<Embroidery> page = embroideryDao.search(pageable);
        List<EmbroideryWrapper> embroideryWrapperList = new ArrayList<>();
        for (Embroidery embroidery : page.getContent()) {
            EmbroideryWrapper articleWrapper = new EmbroideryWrapper(embroidery.getId(), embroidery.getReference(),
                    embroidery.getDescription(), embroidery.getRetailPrice(), embroidery.getStitches(), embroidery.getColors(),
                    embroidery.getSquareMillimeters());
            embroideryWrapperList.add(articleWrapper);
        }
        return new PageImpl<EmbroideryWrapper>(embroideryWrapperList, pageable, page.getTotalElements());
    }
}
