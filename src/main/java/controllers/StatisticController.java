package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ShoppingDao;
import services.StatisticService;

@Controller
public class StatisticController {

    private ShoppingDao shoppingDao;

    private StatisticService statisticService;

    @Autowired
    public void setShoppingDao(ShoppingDao shoppingDao) {
        this.shoppingDao = shoppingDao;
    }

    @Autowired
    public void setStatisticService(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

}