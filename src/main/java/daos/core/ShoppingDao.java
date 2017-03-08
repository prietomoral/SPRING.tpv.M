package daos.core;


import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Shopping;

public interface ShoppingDao extends JpaRepository<Shopping, Long>{
    /*
     * 
     * 
select description, amount, productId,Ticket_id,created from tpv.shopping as s 
join tpv.ticket_shopping as ts 
on s.id=ts.shoppingList_id 
join tpv.ticket as t on ts.Ticket_id=t.id
where created BETWEEN '2017-02-07 00:00:00' and '2017-03-07 18:17:01'
and productId='84000001111'
*/
    
    //@Query("select s from Ticket t JOIN t.shoppingList s where s.productId = ?1 AND t.created BETWEEN ?2 AND ?3 ORDER BY t.created")
    @Query("select s from Ticket t JOIN t.shoppingList s where s.productId = ?1 AND t.created>?2 and t.created<?3")
    public List<Shopping> findShoppingsProductByDate(long id, Calendar inicio,Calendar fin);
    //public List<Shopping> findShoppingsProductByDate(long product, Calendar dateInicio, Calendar dateFin);

}
