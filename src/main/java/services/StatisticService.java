package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class StatisticService {
/*consulta sql:
    select s.description, sum(s.amount), s.productId from tpv.shopping as s join tpv.ticket_shopping as ts 
    on s.id=ts.shoppingList_id 
    join tpv.ticket as t on ts.Ticket_id=t.id
    group by productId*/
    public void getSoldProductsStatistic(){
        String jpqlConsult="Select s.productId, s.description, SUM(s.amount)";
        //EntityManagerFactory em=Persistence.createEntityManagerFactory("JPA_Factoria"); 
        
    }
}
