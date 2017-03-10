package services;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class StatisticServiceIT {
    @Autowired
    StatisticService statisticService;
    
    @Test
    public void mostSoldProductsMappedTest(){
        List<SemiWrapperStatisticSold> statistics =statisticService.mostSoldProductsMapped();
        System.out.println(statistics.size());
        for (SemiWrapperStatisticSold statistic : statistics) {
            System.out.println(statistic.toString());
        }
        assertTrue(statistics.size()<=5);
    }
}
