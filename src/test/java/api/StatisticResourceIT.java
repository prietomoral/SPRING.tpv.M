package api;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
