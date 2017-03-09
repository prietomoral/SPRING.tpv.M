package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.TextilePrintingDao;
import daos.users.TokenDao;
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
}
