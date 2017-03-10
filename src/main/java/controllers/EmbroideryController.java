package controllers;

import java.math.BigDecimal;
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
        
       // System.out.println("result"+embroideryDao.findAll()); 
        
                  

        for (Embroidery embroidery : embroideryDao.findAll()) {

            EmbroideryWrapper embroideryWrapper = new EmbroideryWrapper();
            embroideryWrapper.setId(embroidery.getId());
            embroideryWrapper.setReference(embroidery.getReference());
            embroideryWrapper.setDescription(embroidery.getDescription());
            embroideryWrapper.setRetailPrice(embroidery.getRetailPrice());
            embroideryWrapper.setStitches(embroidery.getStitches());
            embroideryWrapper.setColors(embroidery.getColors());
            embroideryWrapper.setSquareMillimeters(embroidery.getSquareMillimeters());
            
         /*   embroideryWrapper.setId(1);
            embroideryWrapper.setReference("sas");
            embroideryWrapper.setDescription("zsas");
            embroideryWrapper.setRetailPrice(new BigDecimal(20));
            embroideryWrapper.setStitches(4);
            embroideryWrapper.setColors(5);
            embroideryWrapper.setSquareMillimeters(5);

            embroideryList.add(embroideryWrapper);*/
        }
        return embroideryList;

        
    }

    public void add(EmbroideryWrapper embroideryWrapper) {
        
           /* Embroidery embroidery = new Embroidery(
                    embroideryWrapper.getId(),
                    embroideryWrapper.getReference(),
                    embroideryWrapper.getRetailPrice(),
                    embroideryWrapper.getDescription(),
                    embroideryWrapper.getStitches(),
                    embroideryWrapper.getColors(),                    
                    embroideryWrapper.getSquareMillimeters()
                    );*/
        Embroidery embroidery = new Embroidery(84000002222L + 1, "embroidery" + 1, new BigDecimal(20 + 1), "embroidery" + 1, 1 * 1000, 1, 1 * 10);
            this.embroideryDao.save(embroidery);
        
    }
    public Page<EmbroideryWrapper> search(Pageable pageable) {
        Page<Embroidery> page = embroideryDao.search(pageable);
        List<EmbroideryWrapper> embroideryWrapperList = new ArrayList<>();
        for (Embroidery embroidery : page.getContent()) {
            EmbroideryWrapper embroideryWrapper = new EmbroideryWrapper(embroidery.getId(), embroidery.getReference(),
                    embroidery.getDescription(), embroidery.getRetailPrice(), embroidery.getStitches(), embroidery.getColors(),
                    embroidery.getSquareMillimeters());
            embroideryWrapperList.add(embroideryWrapper);
        }
        return new PageImpl<EmbroideryWrapper>(embroideryWrapperList, pageable, page.getTotalElements());

    }
}
