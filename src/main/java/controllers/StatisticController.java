package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ShoppingDao;
import services.SemiWrapperStatisticSold;
import services.StatisticService;
import wrappers.TotalSoldProductWrapper;

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
    
    public List<TotalSoldProductWrapper> getMostSoldProducts(){
        List<SemiWrapperStatisticSold> semiWrapperMostSoldProducts=statisticService.mostSoldProductsMapped();
        List<TotalSoldProductWrapper> mostSoldProducts=new ArrayList<TotalSoldProductWrapper>();
        for (SemiWrapperStatisticSold sWMSP : semiWrapperMostSoldProducts) {
            TotalSoldProductWrapper soldProduct=new TotalSoldProductWrapper(sWMSP.getIdProduct(),sWMSP.getTotalAmountSold(),sWMSP.getDescription());
            mostSoldProducts.add(soldProduct);
        }
        return mostSoldProducts;
    }

}