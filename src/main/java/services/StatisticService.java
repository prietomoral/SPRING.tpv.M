package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.core.ShoppingDao;

@Service
public class StatisticService {
    @Autowired
    private ShoppingDao shopingDao;
    
    public List<SemiWrapperStatisticSold> mostSoldProductsMapped(){
        List<Object[]> soldList=shopingDao.findTotalSoldsProducts();
        List<SemiWrapperStatisticSold> statistics=new ArrayList<SemiWrapperStatisticSold>();
        for(int i=0;i<soldList.size() && i<5 ;i++){
            Long id=(Long) soldList.get(i)[0];
            String description=(String)soldList.get(i)[1];
            Long totalAmount=(Long)soldList.get(i)[2];
            SemiWrapperStatisticSold soldProduct=new SemiWrapperStatisticSold(id,description,totalAmount);
            statistics.add(soldProduct);
        }
        
        return statistics;
    }
}
