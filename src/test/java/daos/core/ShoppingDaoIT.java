package daos.core;

import static org.junit.Assert.assertTrue;

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
        int diaInicio = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaInicio-1);
        Calendar dateFin=Calendar.getInstance();
        int diaFin = dateFin.get(Calendar.DAY_OF_MONTH);
        dateFin.set(Calendar.DAY_OF_MONTH, diaFin+1);
        long id=84000001111L;
        List<Shopping> shoppings=shoppingDao.findShoppingsProductByDate(id, dateInicio,dateFin);
        for(int i=0;i<shoppings.size();i++){
            System.out.println("Producto "+ i+" "+shoppings.get(i).getDescription());
        }
       assertTrue(shoppings.size()>0);
    }
    
    @Test
    public void testFindTotalSoldsProducts(){
        List<Object[]> mostSoldProducts=shoppingDao.findTotalSoldsProducts();
        for(Object[] s: mostSoldProducts){
            System.out.println(s[0]+" Descripcion: "+s[1]+" Cantidad total: "+s[2]);
        }
        assertTrue(mostSoldProducts.size()>0);
    }
    /*
    @Test
    public void testFindTotalSoldsProductsFormat(){
        List<SemiWrapperStatisticShopping> mostSoldProducts=shoppingDao.findTotalSoldsProductsFormat();
        for(SemiWrapperStatisticShopping s: mostSoldProducts){
            System.out.println("Con clase Wrapper: idProduct "+s.getIdProduct()+" Descripcion: "+s.getDescription()+" Cantidad total: "+s.getTotalAmountSold());
        }
        assertTrue(mostSoldProducts.size()>0);
    }*/
}
