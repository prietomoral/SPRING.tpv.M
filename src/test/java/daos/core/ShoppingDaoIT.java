package daos.core;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Shopping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ShoppingDaoIT {
    @Autowired
    private ShoppingDao shoppingDao;
    
    @Test
    public void testfFindShoppingProductByDate(){
        Calendar dateInicio=Calendar.getInstance();
        dateInicio.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Fecha inicio: "+dateInicio.toString());
        Calendar dateFin=Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, 9);
        long id=84000001111L;
        //List<Shopping> shoppings=shoppingDao.findShoppingsProductByDate(id, dateInicio, dateFin);
        List<Shopping> shoppings=shoppingDao.findShoppingsProductByDate(id, dateInicio,dateFin);
        System.out.println("Longitud "+shoppings.size());
        for(int i=0;i<shoppings.size();i++){
            System.out.println("Producto "+ i+" "+shoppings.get(i).getDescription());
        }
    }
}
