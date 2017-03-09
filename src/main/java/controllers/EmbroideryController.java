package controllers;

import java.util.List;

import daos.core.EmbroideryDao;
import entities.core.Embroidery;

public class EmbroideryController {

    private EmbroideryDao embroideryDao;

    public List<Embroidery> allEmbroidery() {

        return embroideryDao.findAll();

    }

}
