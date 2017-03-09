package controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import daos.core.EmbroideryDao;
import entities.core.Embroidery;

@Controller
public class EmbroideryController {

    private EmbroideryDao embroideryDao;

    public List<Embroidery> allEmbroidery() {

        return embroideryDao.findAll();

    }

}
