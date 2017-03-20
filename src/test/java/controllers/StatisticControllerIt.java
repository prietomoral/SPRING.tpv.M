package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.StatisticDataWrapper;
import wrappers.StatisticProductByDateWrapper;
import wrappers.TotalSoldProductWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})

public class StatisticControllerIt {
    
    @Autowired
    StatisticController statisticController;
    
    @Test
    public void testGetMostSoldProducts(){
        List<TotalSoldProductWrapper> mostSoldProducts= statisticController.getMostSoldProducts();
        assertTrue(mostSoldProducts.size()>0);
    }
    
    @Test
    public void testGetSoldsOfProductByDate(){
        Long id=84000001111L;
        Calendar inicio=Calendar.getInstance();
        int diaInicio = inicio.get(Calendar.DAY_OF_MONTH);
        inicio.set(Calendar.DAY_OF_MONTH, diaInicio-1);
        Calendar fin=Calendar.getInstance();
        int diaFin = fin.get(Calendar.DAY_OF_MONTH);
        fin.set(Calendar.DAY_OF_MONTH, diaFin+1);
        StatisticDataWrapper statisticData=new StatisticDataWrapper(id,inicio,fin);
        List<StatisticProductByDateWrapper> statistByDate=statisticController.getSoldsOfProductByDate(statisticData);
        assertTrue(statistByDate.size()>0);
        StatisticProductByDateWrapper statistic=statistByDate.get(0);
        assertEquals(statistic.getDescription(),"article0");
    }
}
