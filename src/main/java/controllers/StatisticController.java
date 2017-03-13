package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ShoppingDao;
import entities.core.Shopping;
import services.SemiWrapperStatisticSold;
import services.StatisticService;
import wrappers.StatisticProductByDateWrapper;
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
    
    public List<StatisticProductByDateWrapper> getSoldsOfProductByDate(long id, Calendar inicio,Calendar fin){
        List<StatisticProductByDateWrapper> listStatisticProduct=new ArrayList<StatisticProductByDateWrapper>();
        List<Shopping> listShoppingByDate=shoppingDao.findShoppingsProductByDate(id, inicio, fin);
        for (Shopping shopping : listShoppingByDate) {
            StatisticProductByDateWrapper productByDateW=new StatisticProductByDateWrapper(shopping.getId(),shopping.getProductId(),shopping.getDescription(),shopping.getAmount());
            listStatisticProduct.add(productByDateW);
        }
        return listStatisticProduct;
    }

}