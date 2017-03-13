package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controllers.StatisticController;

@RestController
@RequestMapping(Uris.VERSION + Uris.STATISTIC)
public class StatisticResource {
    
    private StatisticController statisticController;

    @Autowired
    public void setStatisticController(StatisticController statisticController) {
        this.statisticController = statisticController;
    }
    
    
}
