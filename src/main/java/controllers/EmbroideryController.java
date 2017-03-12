package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import api.exceptions.AlreadyExistEmbroideryException;
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

		System.out.println("result" + embroideryDao.findAll());
		for (Embroidery embroidery : embroideryDao.findAll()) {

			EmbroideryWrapper embroideryWrapper = new EmbroideryWrapper();
			embroideryWrapper.setId(embroidery.getId());
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

	public void add(EmbroideryWrapper embroideryWrapper) throws AlreadyExistEmbroideryException {

		Embroidery embroideryDB = embroideryDao.findOne(embroideryWrapper.getId());
		if (embroideryDB != null) {
			throw new AlreadyExistEmbroideryException();
		} else {
			Embroidery embroidery = new Embroidery(embroideryWrapper.getId(), embroideryWrapper.getReference(),
					embroideryWrapper.getRetailPrice(), embroideryWrapper.getDescription(),
					embroideryWrapper.getStitches(), embroideryWrapper.getColors(),
					embroideryWrapper.getSquareMillimeters());

			this.embroideryDao.save(embroidery);
		}
	}

	public Page<EmbroideryWrapper> search(Pageable pageable) {
		Page<Embroidery> page = embroideryDao.search(pageable);
		List<EmbroideryWrapper> embroideryWrapperList = new ArrayList<>();
		for (Embroidery embroidery : page.getContent()) {
			EmbroideryWrapper embroideryWrapper = new EmbroideryWrapper(embroidery.getId(), embroidery.getReference(),
					embroidery.getDescription(), embroidery.getRetailPrice(), embroidery.getStitches(),
					embroidery.getColors(), embroidery.getSquareMillimeters());
			embroideryWrapperList.add(embroideryWrapper);
		}
		return new PageImpl<EmbroideryWrapper>(embroideryWrapperList, pageable, page.getTotalElements());

	}

	public void deleteEmbroidery(Integer id) {
		Embroidery embroidery = embroideryDao.findOne(Long.valueOf(id));
		embroideryDao.delete(embroidery);

	}
}
