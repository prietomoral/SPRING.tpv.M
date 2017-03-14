package api;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import wrappers.StatisticDataWrapper;
import wrappers.StatisticProductByDateWrapper;
import wrappers.TotalSoldProductWrapper;

public class StatisticResourceIT {

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }
    
    @Test
    public void testGetPopularProducts() {
        List<TotalSoldProductWrapper> popularProducts = Arrays.asList(new RestBuilder<TotalSoldProductWrapper[]>(RestService.URL)
                .path(Uris.STATISTIC + Uris.POPULAR_PRODUCTS).clazz(TotalSoldProductWrapper[].class)
                .get().build());
        assertNotNull(popularProducts);
        System.out.println(popularProducts.size());
        for (TotalSoldProductWrapper totalSoldProductWrapper : popularProducts) {
            System.out.println(totalSoldProductWrapper.toString());
        }
    }
    
    @Test
    public void testGetProductSalesInTime(){
        Long id=84000003336L;
        Calendar inicio=Calendar.getInstance();
        int diaInicio = inicio.get(Calendar.DAY_OF_MONTH);
        inicio.set(Calendar.DAY_OF_MONTH, diaInicio-1);
        Calendar fin=Calendar.getInstance();
        int diaFin = fin.get(Calendar.DAY_OF_MONTH);
        fin.set(Calendar.DAY_OF_MONTH, diaFin+1);
        List<StatisticProductByDateWrapper> statisticsDate=Arrays.asList(new RestBuilder<StatisticProductByDateWrapper[]>(RestService.URL)
                .path(Uris.STATISTIC + Uris.BY_DATE).body(new StatisticDataWrapper(id,inicio,fin))
                .clazz(StatisticProductByDateWrapper[].class)
                .post().build());
        assertNotNull(statisticsDate);
        System.out.println(statisticsDate.size());
        for (StatisticProductByDateWrapper statisticProductByDateWrapper : statisticsDate) {
            System.out.println(statisticProductByDateWrapper.toString());
        }
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
