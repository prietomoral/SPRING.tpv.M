package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import daos.core.ShoppingDao;

@Service
public class SatisticService {
    @Autowired
    private ShoppingDao shopingDao;
    
    public SemiWrapperStatisticSold mostSoldProductsMapped(){
        SemiWrapperStatisticSold statistic=new SemiWrapperStatisticSold();
        return statistic;
    }
}
