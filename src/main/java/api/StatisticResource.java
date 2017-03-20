package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.StatisticController;
import wrappers.StatisticDataWrapper;
import wrappers.StatisticProductByDateWrapper;
import wrappers.TotalSoldProductWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.STATISTIC)
public class StatisticResource {

    private StatisticController statisticController;

    @Autowired
    public void setStatisticController(StatisticController statisticController) {
        this.statisticController = statisticController;
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @RequestMapping(value = Uris.POPULAR_PRODUCTS, method = RequestMethod.GET)
    public List<TotalSoldProductWrapper> getPopularProducts() {
        return this.statisticController.getMostSoldProducts();
    }

    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @RequestMapping(value = Uris.BY_DATE, method = RequestMethod.POST)
    public List<StatisticProductByDateWrapper> getProductSalesInTime(@RequestBody StatisticDataWrapper statisticData) {
        return statisticController.getSoldsOfProductByDate(statisticData);
    }
}