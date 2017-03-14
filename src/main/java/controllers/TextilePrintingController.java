package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import api.exceptions.AlreadyExistProductException;
import daos.core.TextilePrintingDao;
import entities.core.TextilePrinting;
import wrappers.TextilePrintingWrapper;

@Controller
public class TextilePrintingController {
    private TextilePrintingDao textilePrintingDao;

    @Autowired
    public void setTextilePrintingDao(TextilePrintingDao textilePrintingDao) {
        this.textilePrintingDao = textilePrintingDao;
    }

    public List<TextilePrintingWrapper> all() {
        ArrayList<TextilePrintingWrapper> listTextilePrintingWrapper = new ArrayList<TextilePrintingWrapper>();

        for (TextilePrinting textilePrinting : textilePrintingDao.findAll()) {
            TextilePrintingWrapper textilePrintingWrapper = new TextilePrintingWrapper();

            textilePrintingWrapper.setId(textilePrinting.getId());
            textilePrintingWrapper.setReference(textilePrinting.getReference());
            textilePrintingWrapper.setDescription(textilePrinting.getDescription());
            textilePrintingWrapper.setRetailPrice(textilePrinting.getRetailPrice());
            textilePrintingWrapper.setType(textilePrinting.getType());

            listTextilePrintingWrapper.add(textilePrintingWrapper);
        }

        return listTextilePrintingWrapper;
    }

    public void add(TextilePrintingWrapper textilePrintingWrapper) throws AlreadyExistProductException {
        TextilePrinting textilePrintingDB = textilePrintingDao.findOne(textilePrintingWrapper.getId());
        if (textilePrintingDB != null) {
            throw new AlreadyExistProductException();
        } else {
            TextilePrinting textilePrinting = new TextilePrinting(textilePrintingWrapper.getId(), textilePrintingWrapper.getReference(),
                    textilePrintingWrapper.getRetailPrice(), textilePrintingWrapper.getDescription(), textilePrintingWrapper.getType());

            this.textilePrintingDao.save(textilePrinting);
        }
    }

    public Page<TextilePrintingWrapper> search(Pageable pageable, String reference, String description, BigDecimal minRetailPrice,
            BigDecimal maxRetailPrice, String type) {
        Page<TextilePrinting> page = textilePrintingDao.search(pageable, reference, description, minRetailPrice, maxRetailPrice, type);
        List<TextilePrintingWrapper> textilePrintingWrapperList = new ArrayList<>();
        for (TextilePrinting textilePrinting : page.getContent()) {
            textilePrintingWrapperList.add(new TextilePrintingWrapper(textilePrinting));
        }
        return new PageImpl<TextilePrintingWrapper>(textilePrintingWrapperList, pageable, page.getTotalElements());
    }

    public void delete(long id) {
        TextilePrinting textilePrinting = textilePrintingDao.findOne(id);
        textilePrintingDao.delete(textilePrinting);
    }

    public void edit(TextilePrintingWrapper textilePrintingWrapper) {

    	TextilePrinting textilePrintingDB = textilePrintingDao.findOne(textilePrintingWrapper.getId());

        textilePrintingDB.setReference(textilePrintingWrapper.getReference());
        textilePrintingDB.setDescription(textilePrintingWrapper.getDescription());
        textilePrintingDB.setRetailPrice(textilePrintingWrapper.getRetailPrice());
        textilePrintingDB.setType(textilePrintingWrapper.getType());

        this.textilePrintingDao.save(textilePrintingDB);
    }

	public TextilePrinting findOne(long id) {
		TextilePrinting textilePrinting = textilePrintingDao.findOne(id);
        return textilePrinting;
	}
}
