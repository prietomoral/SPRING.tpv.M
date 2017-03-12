package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import api.exceptions.AlreadyExistTextilePrintingException;
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

	public void add(TextilePrintingWrapper textilePrintingWrapper) throws AlreadyExistTextilePrintingException {
		TextilePrinting textilePrinting = new TextilePrinting(textilePrintingWrapper.getId(),
				textilePrintingWrapper.getReference(), textilePrintingWrapper.getRetailPrice(),
				textilePrintingWrapper.getDescription(), textilePrintingWrapper.getType());

		this.textilePrintingDao.save(textilePrinting);
	}

	public Page<TextilePrintingWrapper> search(Pageable pageable) {
		Page<TextilePrinting> page = textilePrintingDao.search(pageable);
		List<TextilePrintingWrapper> textilePrintingWrapperList = new ArrayList<>();

		for (TextilePrinting textilePrinting : page.getContent()) {
			TextilePrintingWrapper textilePrintingWrapper = new TextilePrintingWrapper(textilePrinting.getId(),
					textilePrinting.getReference(), textilePrinting.getDescription(), textilePrinting.getRetailPrice(),
					textilePrinting.getType());

			textilePrintingWrapperList.add(textilePrintingWrapper);
		}
		return new PageImpl<TextilePrintingWrapper>(textilePrintingWrapperList, pageable, page.getTotalElements());
	}

	public void delete(int id) {
		TextilePrinting textilePrinting = textilePrintingDao.findOne(Long.valueOf(id));
		textilePrintingDao.delete(textilePrinting);
	}
}
